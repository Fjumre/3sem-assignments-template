package app.jpaLifecycleAndAnnotations;



public class StudentDAOMain {

    public static void main(String[] args) {

        StudentDAO studentDAO = new StudentDAO();


        Student student = new Student("Johnny", "Bravo", "johnny@mail.dk", 25);
        studentDAO.create(student);



        Student foundStudent = studentDAO.read(student.getId());
        System.out.println("Found Student: " + foundStudent.getName());


        foundStudent.setEmail("mail@mail.dk");
        Student updatedStudent = studentDAO.update(foundStudent);
        System.out.println("Updated Email: " + updatedStudent.getEmail());

        studentDAO.delete(updatedStudent.getId());
        System.out.println("Student deleted");


        Student deletedStudent = studentDAO.read(updatedStudent.getId());
        if (deletedStudent == null) {
            System.out.println("Student not found (expected since it was deleted).");
        }
    }
}


//Explain the benefits of using a DAO architecture for separating database access logic from business logic.
//It gives you a better overview and easier to maintain, test and a more precise code since it becomes easiere to use with a database