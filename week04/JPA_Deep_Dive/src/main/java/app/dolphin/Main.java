package app.dolphin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {


        EntityManagerFactory emf= HibernateConfig.getEntityManagerFactoryConfig();

        try ( EntityManager em = emf.createEntityManager()){

            Person p1= new Person("Hanzi");
            PersonDetails pd1= new PersonDetails("Algade 3", "Ronne", 4300, 25);
            p1.addPersonDetails(pd1);
            Fee f1= new Fee(125, LocalDate.now());
            Fee f2= new Fee(150, LocalDate.now());
            p1.addFee(f1);
            p1.addFee(f2);
            Note n1= new Note("Good man", LocalDate.now(), "Kurt");
            p1.addNote(n1);
            Event ev1= new Event("DM i senior", LocalDate.now());
            Event ev2= new Event("DM juinor", LocalDate.now());
            p1.addEvent(p1, ev1, LocalDate.now(), 175);
            p1.addEvent(p1, ev2, LocalDate.now(), 225);

            em.getTransaction().begin();
            em.persist(ev1);
            em.persist(ev2);
            em.persist(p1);
            em.getTransaction().commit();

        }
    }
}