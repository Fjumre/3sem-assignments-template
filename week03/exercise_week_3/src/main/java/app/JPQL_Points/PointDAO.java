package app.JPQL_Points;

import app.jpaLifecycleAndAnnotations.Student;
import jakarta.persistence.*;

import java.util.List;



public class PointDAO {


    private EntityManagerFactory emf;

    public PointDAO() {
        emf = HibernateConfig.getEntityManagerFactoryConfig();
    }

    public void storePoints() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            for (int i = 0; i < 1000; i++) {
                Point p = new Point(i, i);
                em.persist(p);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }


    public long getTotalPoints() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT COUNT(p) FROM Point p");
            return (long) query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public double getAverageX() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT AVG(p.x) FROM Point p");
            return (double) query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Point> retrieveAllPoints() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Point> query = em.createQuery("SELECT p FROM Point p", Point.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Make sure to close EntityManagerFactory when the application is shutting down
    public void close() {
        if (emf != null) {
            emf.close();
        }
    }

    public String getName() {
        try (var em = emf.createEntityManager()) {

            Query query = em.createQuery("SELECT firstName, lastName from Student", Student.class);
            return query.toString();
        }

    }
}
