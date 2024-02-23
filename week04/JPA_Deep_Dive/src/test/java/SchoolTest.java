
import app.jpa_rm_jpql.HibernateConfig;
import app.jpa_rm_jpql.Semester;
import app.jpa_rm_jpql.Student;
import app.jpa_rm_jpql.Teacher;
import app.jpa_rm_jpql.dao.StudentDAOImpl;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SchoolTest {

    private static EntityManagerFactory emfTest;

    private static StudentDAOImpl studentDAOImpl;


    @BeforeAll
    static void setUp() {
        emfTest = HibernateConfig.getEntityManagerFactoryConfigTest();
        studentDAOImpl= new StudentDAOImpl(emfTest);
    }

    @AfterAll
    static void tearDown() {
        if (emfTest != null) {
            emfTest.close();
        }
    }


    @Test
    public void findAllStudentsByFirstNameTest() {
        String testName= "Anders";
       List<Student> studentList= studentDAOImpl.findAllStudentsByFirstName(testName);

        assertNotNull(studentList);
    }

    @Test
    public void findAllStudentsByLastNameTest() {
        String testName= "And";
        List<Student> studentList= studentDAOImpl.findAllStudentsByLastName(testName);

        assertNotNull(studentList);
    }

    @Test
    public void findTotalNumberOfStudentsBySemesterTest(){
        String semesterTestName= "Fall 2021";
        long studentNumber= studentDAOImpl.findTotalNumberOfStudentsBySemester(semesterTestName);


        assertNotNull(studentNumber);


    }

    @Test
    public void findTotalNumberOfStudentsByTeacherTest(){

        Teacher teacher = studentDAOImpl.findTeacherByLastName("Karlsen");

        long studentNumber= studentDAOImpl.findTotalNumberOfStudentsByTeacher(teacher);


        assertTrue(studentNumber >= 0, "The number of students should be non-negative.");
    }

}

