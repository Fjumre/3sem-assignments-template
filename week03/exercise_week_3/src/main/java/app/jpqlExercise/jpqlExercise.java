package app.jpqlExercise;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.hibernate.sql.Update;

import java.util.List;

public class jpqlExercise {
    static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public static void main(String[] args) {


        char initial = 'E';//To find the initial of the first letter in a Name

        for (Employee employee1 : readAllEmployees()) {
            System.out.println(employee1);
        }

        for (Employee employee2 : salaryAmount()) {
            System.out.println(employee2);
        }

        for (Employee employee3 : department()) {
            System.out.println(employee3);
        }

        for (Employee employee4 : namesInitial(initial)) {
            System.out.println(employee4);
        }

        updateSalary();
        updateDepartment();


    }

    public static Employee read(int id) {

        try (EntityManager em = emf.createEntityManager()) {
            Employee employee = em.find(Employee.class, id);
            return employee;
        }
    }

    public static List<Employee> readAllEmployees() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Employee> typedQuery = em.createNamedQuery("Employee.findAll", Employee.class);

            return typedQuery.getResultList();
        }
    }

    public static Employee findById(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Employee.class, id);
        }
    }

    public static List<Employee> salaryAmount() {
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery("SELECT e from Employee e where salary > 60000", Employee.class);

            return query.getResultList();
        }
    }

    public static List<Employee> department() {
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery("SELECT e from Employee e where e.department = :department", Employee.class);

            query.setParameter("department", "Sales");

            return query.getResultList();
        }
    }

    public static List<Employee> namesInitial(char initial) {
        try (EntityManager em = emf.createEntityManager()) {

            TypedQuery<Employee> typedQuery = em.createQuery("SELECT e from Employee e where e.first_name LIKE :initial ", Employee.class);

            typedQuery.setParameter("initial", initial + "%");

            return typedQuery.getResultList();
        }
    }

    public static int updateSalary() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Query query = em.createQuery("UPDATE Employee e set e.salary= salary+13000 where e.last_name = :last_name", Employee.class);

            query.setParameter("last_name", "Wilson");

            int updatedCount = query.executeUpdate();
            em.getTransaction().commit();
            return updatedCount;
        }
    }

    public static void updateDepartment() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Query query= em.createQuery("UPDATE Employee e set e.department ='Finance' where e.first_name like ?1 and e.last_name like ?2", Employee.class);

            query.setParameter(1, "Matthew");
            query.setParameter(2, "Taylor");

            query.executeUpdate();
            em.getTransaction().commit();        }
    }
}
