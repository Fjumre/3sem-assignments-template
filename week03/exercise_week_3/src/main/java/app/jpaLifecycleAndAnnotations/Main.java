package app.jpaLifecycleAndAnnotations;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

import java.util.List;

public class Main {


    static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public static void main(String[] args) {

        /*List<Student> students = Arrays.asList(
                new Student("Marie", "Hansen", "hh@mail.dk", 23),
                new Student("Sam", "Johansen", "dd@mail.dk", 23),

        );
        for (Student student : students) {
            createStudent(student);
        }*/

        System.out.println(readAllStudents());

    }

    public static void createStudent(Student student) {
        try  (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Student readStudent(int id) {
        try (EntityManager em = emf.createEntityManager()) {

            Student student= em.find(Student.class, id);//Finds the student from the id info
            return student;
        }
    }

    public static Student updateStudent(Student student){
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Student updatedStudent= em.merge(student);//Updates/merges the new info into the students info
            em.getTransaction().commit();
            return updatedStudent;

        }
    }

    public static void deleteStudent(int id){
        try (EntityManager em = emf.createEntityManager()){

            em.getTransaction().begin();
            Student student = readStudent(id);// Directly find the student within the same EntityManager context
            if (student != null) {
                em.remove(student);//Removes the student
            }
            em.getTransaction().commit();
        }
        }

    public static List<Student> readAllStudents(){
        try (var em = emf.createEntityManager()){

            //Finds all the students from the students database in student table
            Query query = em.createQuery("SELECT s from Student s", Student.class);

            List<Student> studentList= query.getResultList();
                return studentList;

            //return query.getResultList();
        }
    }
    }
