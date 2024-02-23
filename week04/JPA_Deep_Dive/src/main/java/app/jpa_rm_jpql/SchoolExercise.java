package app.jpa_rm_jpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class SchoolExercise {

    public static void main(String[] args) {
        EntityManagerFactory emf= HibernateConfig.getEntityManagerFactoryConfig();
        try ( EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();

            Teacher teacher1 = new Teacher("John", "Karlsen");
            Teacher teacher2 = new Teacher("Line", "Fransen");

            Student student1 = new Student("Anders", "And");
            Student student2 = new Student("Bob", "Johnsen");
            Student student3 = new Student("Karl", "Ibsen");
            Student student4 = new Student("Emma", "Johanssen");
            Student student5 = new Student("Noah", "Larsen");
            Student student6 = new Student("Olivia", "Gustavsen");
            Student student7 = new Student("William", "Andersson");
            Student student8 = new Student("Evelin", "Svensen");
            Student student9 = new Student("James", "Lindberg");
            Student student10 = new Student("Isabella", "Magnusson");


            Semester semester1 = new Semester("Fall 2021");
            Semester semester2 = new Semester("Spring 2025");

            teacher1.addSemester(semester1);
            teacher2.addSemester(semester2);

            semester1.addStudent(student1);
            semester1.addStudent(student2);
            semester1.addStudent(student3);
            semester1.addStudent(student4);
            semester1.addStudent(student5);
            semester1.addStudent(student6);
            semester2.addStudent(student7);
            semester2.addStudent(student8);
            semester2.addStudent(student9);
            semester2.addStudent(student10);

            em.persist(semester1);
            em.persist(semester2);
            em.persist(teacher1);
            em.persist(teacher2);
            em.persist(student1);
            em.persist(student2);
            em.persist(student3);
            em.persist(student4);
            em.persist(student5);
            em.persist(student6);
            em.persist(student7);
            em.persist(student8);
            em.persist(student9);
            em.persist(student10);
            em.getTransaction().commit();
        }
        }
    }

