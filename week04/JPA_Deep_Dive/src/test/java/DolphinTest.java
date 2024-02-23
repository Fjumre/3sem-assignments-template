import app.dolphin.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class DolphinTest {


    private static EntityManagerFactory emfTest;
    private static DolphinDAO dolphinDAO;



    @BeforeAll
    static void setUp() {
        emfTest = HibernateConfig.getEntityManagerFactoryConfigTest();
        dolphinDAO= new DolphinDAO(emfTest);
    }

    @AfterAll
    static void tearDown() {
        if (emfTest != null) {
            emfTest.close();
        }
    }

    @Test
    void testCreate() {
        try (EntityManager em = emfTest.createEntityManager()) {
            Person person = new Person("Jens");
            PersonDetails personDetails= new PersonDetails("Gaden 72", "Tarm", 9230, 55);

            person.addPersonDetails(personDetails);

            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();

            assertNotNull(person);


        }
    }

    @Test
    void testRead() {
        try (EntityManager em = emfTest.createEntityManager()) {
            Person person = new Person("Jens");
            Person findPerson = em.find(Person.class, person.getId());

            System.out.println(findPerson);
        }
    }

    @Test
    void testUpdate() {

        Person person = new Person("Jens");
        Note n1= new Note("Yes man", LocalDate.now(), "Kurt");
        person.addNote(n1);
        dolphinDAO.update(person);


    }

    @Test
    void testDelete() {
        try (EntityManager em = emfTest.createEntityManager()) {
            Person person = new Person("Jens");
            dolphinDAO.delete(person.getId()); // Delete the package


            Person deletedPerson = em.find(Person.class, person.getId());
            assertNull(deletedPerson);

        }
    }
}
