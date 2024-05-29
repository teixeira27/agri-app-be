package org.acme.service;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.acme.domain.Collaborator;
import org.acme.dto.inbound.*;
import org.acme.dto.outbound.CollaboratorDetailsDTO;
import org.acme.dto.outbound.CollaboratorInfoDTO;
import org.acme.dto.outbound.LoginInfoDTO;
import org.acme.repository.CollaboratorRepository;
import org.acme.utils.EmailValidator;
import org.acme.utils.JwtUtils;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;


@ApplicationScoped
public class CollaboratorService {

    private static final Random random = new Random();
    public static final int PIN_LENGTH = 6;

    public static final String VERIFY_MESSAGE = "To verify your account, please insert this PIN ";
    public static final String RESET_MESSAGE = "To reset your password, please insert this PIN ";

    @Inject
    CollaboratorRepository collaboratorRepository;

    @Inject
    Mailer mailer;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public CollaboratorInfoDTO createCollaborator(CollaboratorCreationDTO requestDTO) {
        String pin = this.generatePin();
        Collaborator user = findByEmail(requestDTO.getEmail());
        if (user == null) {
            if (EmailValidator.isValidEmail(requestDTO.getEmail())){
                Collaborator collaborator = Collaborator.builder()
                        .name(requestDTO.getName())
                        .email(requestDTO.getEmail())
                        .password(BcryptUtil.bcryptHash(requestDTO.getPassword()))
                        .pin(pin)
                        .verified(false)
                        .build();
                String context = "verify";
                this.mailSender(requestDTO.getEmail(), pin, context);
                this.collaboratorRepository.save(collaborator);
                return CollaboratorInfoDTO.builder()
                        .collaboratorId(collaborator.getCollaboratorId())
                        .build();
            }
            else throw new IllegalArgumentException("Invalid email!");

        } else throw new EntityNotFoundException("User already exist!");
    }

    @Transactional
    public boolean verifyEmail(EmailVerificationDTO requestDTO) {
        Collaborator user = this.findById(requestDTO.getCollaboratorId());
        if (!user.getVerified() && Objects.equals(user.getPin(), requestDTO.getPin())) {
            user.setVerified(true);
            user.setPin(null);
            this.collaboratorRepository.save(user);
            return true;
        }
        return false;
    }

    public LoginInfoDTO verifyCollaborator(CollaboratorLoginDTO requestDTO) throws Exception {
        Collaborator user = findByEmail(requestDTO.getEmail());
        if (user != null && BcryptUtil.matches(requestDTO.getPassword(), user.getPassword())) {
            int companyId = (user.getCompany() != null) ? user.getCompany().getCompanyId() : 0;
            return LoginInfoDTO.builder()
                    .collaboratorId(user.getCollaboratorId())
                    .token(JwtUtils.generateToken(requestDTO.getEmail()))
                    .companyId(companyId)
                    .build();
        } else throw new EntityNotFoundException("User doesn't exist!");
    }

    @Transactional
    public CollaboratorInfoDTO resetPassword(ResetPasswordDTO requestDTO) {
        Collaborator user = findByEmail(requestDTO.getEmail());
        if (user == null) throw new EntityNotFoundException("User doesn't exist!");

        String pin = this.generatePin();
        String context = "reset";
        user.setPin(pin);
        this.collaboratorRepository.save(user);
        this.mailSender(requestDTO.getEmail(), pin, context);

        return CollaboratorInfoDTO.builder().collaboratorId(user.getCollaboratorId()).build();
    }

    @Transactional
    public boolean newPassword(NewPasswordDTO requestDTO) {
        Collaborator user = this.findById(requestDTO.getCollaboratorId());
        if (Objects.equals(user.getPin(), requestDTO.getPin())) {
            user.setPassword(BcryptUtil.bcryptHash(requestDTO.getNewPassword()));
            user.setPin(null);
            this.collaboratorRepository.save(user);
            return true;
        }
        return false;
    }

    private Collaborator findByEmail(String email) {
        try {
            return entityManager.createQuery("SELECT u FROM Collaborator u WHERE u.email = :email", Collaborator.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public Collaborator findById(Integer id) {
        return collaboratorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Collaborator not found."));
    }

    public CollaboratorDetailsDTO getCollaboratorDetails(Integer id){
        Optional<Collaborator> collaborator = this.collaboratorRepository.findById(id);
        if (collaborator.isEmpty()) throw new EntityNotFoundException("Collaborator not found.");

        Collaborator col = collaborator.get();

        return CollaboratorDetailsDTO.builder()
                .name(col.getName())
                .email(col.getEmail())
                .companyName(col.getCompany().getName())
                .Vat(col.getCompany().getVat())
                .build();
    }

    @Transactional
    public String deleteById(Integer id) {
        collaboratorRepository.deleteById(id);
        return ("Collaborator deleted successfully.");
    }

    private void mailSender(String email, String pin, String context) {
        if (context.equals("verify")) {
            this.mailer.send(Mail.withText(email, "Agri App Verification Account",
                    VERIFY_MESSAGE + pin + " in the app."));
        }else if (context.equals("reset")) {
            this.mailer.send(Mail.withText(email, "Agri App Verification Account",
                    RESET_MESSAGE + pin + " in the app."));
        }
    }

    private String generatePin() {
        StringBuilder pin = new StringBuilder();
        for (int i = 0; i < PIN_LENGTH; i++) {
            pin.append(random.nextInt(10));
        }
        return pin.toString();
    }
}
