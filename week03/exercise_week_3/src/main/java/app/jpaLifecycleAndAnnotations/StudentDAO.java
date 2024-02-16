package app.jpaLifecycleAndAnnotations;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;


public class StudentDAO {

    private EntityManagerFactory emf;

    public StudentDAO() {
        this.emf = HibernateConfig.getEntityManagerFactoryConfig();
    }

    public void create(Student student) {
        try (EntityManager em = emf.createEntityManager()) {

            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        }
    }


    public Student update(Student student) {
        try (EntityManager em = emf.createEntityManager()) {

            em.getTransaction().begin();
            Student updatedStudent = em.merge(student);
            em.getTransaction().commit();
            return updatedStudent;
        }
    }

    public void delete(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Student student = em.find(Student.class, id);
            if (student != null) {
                em.remove(student);
            }
            em.getTransaction().commit();
        }
    }

    public Student read(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            Student student = em.find(Student.class, id);
            return student;
        }
    }


    public void emailIsValid(String email) {
        try (var em = emf.createEntityManager()) {

            Query query = em.createQuery("SELECT email from Student", Student.class);

            if (query.equals(email) || email != "@") {
                throw new RuntimeException();
            }
        }
    }

    public interface SDAO {
        Student create(Student student);
        Student read(int id);
        Student update(Student student);
        void delete(int id);
    }
}