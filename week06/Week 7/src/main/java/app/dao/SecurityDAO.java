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

public class SecurityDAO implements ISecurityDAO{

    private EntityManagerFactory emf;
    private EntityManager entityManager;

    public SecurityDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    @Override
    public User getVerifiedUser(String username, String password) throws ValidationException {
        try (EntityManager em = getEntityManager()) {
            List<User> users = em.createQuery("SELECT u FROM User u").getResultList();
            users.stream().forEach(user -> System.out.println(user.getUsername() + " " + user.getPassword()));
            User user = em.find(User.class, username);
            if (user == null)
                throw new EntityNotFoundException("No user found with username: " + username); //RuntimeException
            user.getRoles().size(); // force roles to be fetched from db
            if (!user.verifyPassword(password))
                throw new ValidationException("Wrong password");
            return user;
        }
    }

    @Override
    public User createUser(String username, String password) {

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        User newUser = new User(username, hashedPassword);


        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newUser);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Failed to create user", e);
        }

        return newUser;
    }

    @Override
    public Role createRole(String role) {
        Role existingRole = entityManager.find(Role.class, role);
        if (existingRole != null) {
            throw new IllegalStateException("Role already exists with roleName: " + role);
        }

        // If the role doesn't exist, create and persist a new Role object
        Role newRole = new Role(role);

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newRole);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback(); // Rollback in case of an exception
            // Handle or rethrow the exception as appropriate for your application
            throw new RuntimeException("Failed to create role due to: " + e.getMessage(), e);
        }

        return newRole;
    }


    @Override
    public User addUserRole(String username, String roleName) {

        EntityTransaction transaction = entityManager.getTransaction();

        User user;
        try {
            transaction.begin();

            user = entityManager.createQuery("SELECT u " +
                            "FROM User u " +
                            "WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
            Role role = entityManager.createQuery("SELECT r " +
                            "FROM Role r " +
                            "WHERE r.roleName = :roleName", Role.class)
                    .setParameter("roleName", roleName)
                    .getSingleResult();

            user.addRole(role); // Modify the collection in the managed entity

            entityManager.merge(user); // Ensure changes are cascaded to the database

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to add role to user due to: " + e.getMessage(), e);
        }

        return user;

    }
}
