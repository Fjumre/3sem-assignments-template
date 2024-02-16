package app.jpaLifecycleAndAnnotations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class JPAStudentDAO implements StudentDAO.SDAO {

    static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    @Override
    public Student create(Student student) {
        try  (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    @Override
    public Student read(int id) {
        try (EntityManager em = emf.createEntityManager()) {

            Student student= em.find(Student.class, id);//Finds the student from the id info
            return student;
        }
    }

    @Override
    public Student update(Student student) {
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Student updatedStudent= em.merge(student);//Updates/merges the new info into the students info
            em.getTransaction().commit();
            return updatedStudent;

        }
    }

    @Override
    public void delete(int id) {
        try (EntityManager em = emf.createEntityManager()){

            em.getTransaction().begin();
            Student student = em.find(Student.class, id);
            if (student != null) {
                em.remove(student);//Removes the student
            }
            em.getTransaction().commit();
        }
    }
}
