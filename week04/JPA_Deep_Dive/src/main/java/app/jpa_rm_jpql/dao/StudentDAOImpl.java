package app.jpa_rm_jpql.dao;

import app.jpa_rm_jpql.HibernateConfig;
import app.jpa_rm_jpql.Semester;
import app.jpa_rm_jpql.Student;
import app.jpa_rm_jpql.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public StudentDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Student> findAllStudentsByFirstName(String firstName) {
        List<Student> studentsFirstName;
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.firstName = :firstName", Student.class);
            query.setParameter("firstName", firstName);

            studentsFirstName = query.getResultList();
        }
        return studentsFirstName;
    }


    @Override
    public List<Student> findAllStudentsByLastName(String lastName) {
        List<Student> studentsLastName;
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.lastName = :lastName", Student.class);
            query.setParameter("lastName", lastName);

            studentsLastName = query.getResultList();
        }
        return studentsLastName;
    }


    @Override
    public long findTotalNumberOfStudentsBySemester(String semester_name) {
        long totalStudents = 0;
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery("SELECT COUNT(s) FROM Student s JOIN s.semester sem WHERE sem.semester_name = :semester_name");
            query.setParameter("semester_name", semester_name);

            totalStudents = (long) query.getSingleResult();

        }
        return totalStudents;
    }

    @Override
    public long findTotalNumberOfStudentsByTeacher(Teacher teacher) {
        long totalStudentsByTeacher = 0;
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery("SELECT COUNT(distinct s) FROM Student s JOIN s.semester sem JOIN sem.teacherSet t WHERE t = :teacher");
            query.setParameter("teacher", teacher);

            totalStudentsByTeacher = (long) query.getSingleResult();

        }
        return totalStudentsByTeacher;
    }

    @Override
    public Teacher findTeacherWithMostSemesters() {
        Teacher teacherWithMostSemesters = null;
        try (EntityManager em = emf.createEntityManager()) {
            List<Teacher[]> results = em.createQuery(
                            "SELECT t, COUNT(sem) AS NumOfSemesters FROM Teacher t JOIN t.semesterSet sem " +
                                    "GROUP BY t " +
                                    "ORDER BY NumOfSemesters DESC",
                            Teacher[].class)
                    .setMaxResults(1)
                    .getResultList();

            if (!results.isEmpty()) {
                teacherWithMostSemesters = results.get(0)[0];
            }
        }
        return teacherWithMostSemesters;
    }

    @Override
    public Semester[] findSemesterWithFewestStudents() {
        try (EntityManager em = emf.createEntityManager()) {
            List<Semester[]> results = em.createQuery(
                    "SELECT sem.semester_name, " +
                            "COUNT(s) AS NumOfStudents " +
                            "FROM Student s " +
                            "JOIN s.semester sem " +
                            "GROUP BY sem.semester_name " +
                            "ORDER BY NumOfStudents ASC",
                    Semester[].class).setMaxResults(1).getResultList();

            return results.get(0);

        }
    }

    @Override
    public StudentInfo getAllStudentInfo(int id) {
        StudentInfo studentInfo = null;
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<StudentInfo> query = em.createQuery("SELECT s.id, s.firstName, s.lastName, sem.semester_name, t.firstName, t.lastName FROM Student s JOIN s.semester sem JOIN sem.teacherSet t WHERE s.id= :id", StudentInfo.class);
            query.setParameter("id", id);

            studentInfo = query.getSingleResult();

        }
        return studentInfo;
    }
    @Override
    public Teacher findTeacherByLastName(String lastName) {
        try (EntityManager em = emf.createEntityManager()){
            List<Teacher> teachers = em.createQuery("SELECT t FROM Teacher t WHERE t.lastName = :lastName", Teacher.class)
                    .setParameter("lastName", lastName)
                    .getResultList();

            return teachers.isEmpty() ? null : teachers.get(0);
        }
    }
}