package app.jpa_rm_jpql.dao;


import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentInfo {


    private int studentId;
    private String firstName;
    private String lastName;
    private String semesterName;
    private String teacherFirstName;
    private String teacherLastName;

    public StudentInfo(int studentId, String firstName, String lastName, String semesterName, String teacherFirstName, String teacherLastName) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.semesterName = semesterName;
        this.teacherFirstName = teacherFirstName;
        this.teacherLastName = teacherLastName;
    }
}
