package app.jpa_rm_jpql;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "student")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", nullable = false, unique = true)
    private int Id;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;


    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student(String firstName, String lastName, Semester semester) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.semester = semester;
    }

    public void addSemester(Semester semester){
    if (semester!= null){
        this.semester=semester;
    }
    }

    public void Teacher (){

    }
}
