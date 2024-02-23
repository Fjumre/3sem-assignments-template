package app.jpa_rm_jpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Populate {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            // populate the database with data
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}