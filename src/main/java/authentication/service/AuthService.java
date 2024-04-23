package authentication.service;

import authentication.model.Auth;
import authentication.repository.AuthRepository;
import authentication.utils.JwtUtils;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AuthService {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    AuthRepository authRepository;

    @Transactional
    public String createAuth (String email, String password) throws Exception {
        Auth user = findByEmail(email);
        if (user == null){
            Auth auth = Auth.builder()
                    .email(email)
                    .password(BcryptUtil.bcryptHash(password))
                    .build();
            authRepository.save(auth);
            BcryptUtil.bcryptHash(password);
            return JwtUtils.generateToken(email);
        }
        else throw new EntityNotFoundException("User already exist!");
    }

    public String authenticate (String email, String password) throws Exception {
        Auth user = findByEmail(email);
        if (user != null && BcryptUtil.matches(password,user.getPassword())){
            return JwtUtils.generateToken(email);
        }
        else throw new EntityNotFoundException("User doesn't exist!");
    }

    private Auth findByEmail(String email) {
        try {
            return entityManager.createQuery("SELECT u FROM Auth u WHERE u.email = :email", Auth.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
