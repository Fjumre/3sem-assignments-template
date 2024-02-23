package app.dolphin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

import java.util.List;

public class DolphinDAO {


    private EntityManagerFactory emf;

    public DolphinDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void create(Person person) {
        try (EntityManager em = emf.createEntityManager()) {

            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        }
    }


    public Person update(Person person) {
        try (EntityManager em = emf.createEntityManager()) {


            em.getTransaction().begin();
            Person updatedPerson = em.merge(person);
            em.getTransaction().commit();
            return updatedPerson;
        }
    }

    public void delete(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Person person = em.find(Person.class, id);
            if (person != null) {
                em.remove(person);
            }
            em.getTransaction().commit();
        }
    }

    public Person read(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            Person person = em.find(Person.class, id);
            return person;
        }
    }

    public double totalPayments(int id){
        Double totalPayments = 0.0;
        try (EntityManager em = emf.createEntityManager()) {
            Query query= em.createQuery("SELECT SUM(f.amount) FROM Fee f WHERE f.person.id = :id");
            query.setParameter("id", id);


            totalPayments = (Double) query.getSingleResult();

        } return totalPayments != null ? totalPayments : 0.0;
    }

    public List<String> allNotes(int id){
        List<String> notes;
        try (EntityManager em = emf.createEntityManager()) {
            Query query= em.createQuery("SELECT n.infonote FROM Note n WHERE n.person.id = :id");
            query.setParameter("id", id);

            notes = query.getResultList();
        }
        return notes;
    }
    public List<Object[]> allNotesNameAge(int id){
        List<Object[]> notesNameAndAge;
        try (EntityManager em = emf.createEntityManager()) {
            Query query= em.createQuery("SELECT n.infonote, p.name, pd.age FROM Note n join Person p join PersonDetails pd WHERE p.id = :id");
            query.setParameter("id", id);

            notesNameAndAge = query.getResultList();
        }
        return notesNameAndAge;
    }
}
