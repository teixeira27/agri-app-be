package authentication.service;

import authentication.model.Auth;
import authentication.utils.JwtUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class AuthService {


    @PersistenceContext
    private EntityManager entityManager;

    public String authenticate (String username, String password) throws Exception {
        Auth user = findByUsername(username);
        if (user != null && user.getUsername().equals(password)){
            return JwtUtils.generateToken(username);
        }
        else throw new EntityNotFoundException("User doesn't exist!");
    }

    private Auth findByUsername(String username) {
        try {
            return entityManager.createQuery("SELECT u FROM auth u WHERE u.username = :username", Auth.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
