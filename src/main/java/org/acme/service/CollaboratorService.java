package org.acme.service;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.acme.domain.Collaborator;
import org.acme.dto.inbound.CollaboratorCreationDTO;
import org.acme.dto.inbound.CollaboratorLoginDTO;
import org.acme.dto.outbound.LoginInfoDTO;
import org.acme.repository.CollaboratorRepository;
import org.acme.utils.JwtUtils;


@ApplicationScoped
public class CollaboratorService {

    @Inject
    CollaboratorRepository collaboratorRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public String createCollaborator(CollaboratorCreationDTO requestDTO) {
        Collaborator user = findByEmail(requestDTO.getEmail());
        if (user == null) {
            Collaborator collaborator = Collaborator.builder()
                    .name(requestDTO.getName())
                    .email(requestDTO.getEmail())
                    .password(BcryptUtil.bcryptHash(requestDTO.getPassword()))
                    .build();
            collaboratorRepository.save(collaborator);
            return "Registration successful, now please log in!";
        } else throw new EntityNotFoundException("User already exist!");
    }

    public LoginInfoDTO verifyCollaborator(CollaboratorLoginDTO requestDTO) throws Exception {
        Collaborator user = findByEmail(requestDTO.getEmail());
        if (user != null && BcryptUtil.matches(requestDTO.getPassword(), user.getPassword())) {
            LoginInfoDTO loginInfoDTO = LoginInfoDTO.builder()
                    .collaboratorId(user.getCollaboratorId())
                    .token(JwtUtils.generateToken(requestDTO.getEmail()))
                    .build();
            return loginInfoDTO;
        } else throw new EntityNotFoundException("User doesn't exist!");
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
        final Collaborator collaborator = collaboratorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Collaborator not found."));
        return collaborator;
    }

    public String deleteById(Integer id) {
        collaboratorRepository.deleteById(id);
        return ("Collaborator deleted successfully.");
    }
}
