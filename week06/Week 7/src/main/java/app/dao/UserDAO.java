package app.dao;

import app.model.Role;
import app.model.User;

import io.javalin.validation.ValidationException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;


public class UserDAO {

    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public void UserService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

//    @Override
//    public User getVerifiedUser(String username, String password) throws ValidationException {
//        User user = entityManager.createQuery("SELECT u " +
//                        "FROM User u " +
//                        "WHERE u.username = :username", User.class)
//                .setParameter("username", username)
//                .getResultList()
//                .stream()
//                .findFirst()
//                .orElseThrow(() -> new ValidationException("User not found"));
//
//        if (!user.verifyPassword(password)) {
//            throw new ValidationException("Invalid password");
//        }
//
//        return user;
//    }





}

