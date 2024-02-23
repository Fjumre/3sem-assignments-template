package app.jpa_rm_jpql;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "teacher")
@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    private int id;
    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @ManyToMany
    private Set<Semester> semesterSet = new HashSet<>();

    public Teacher(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void addSemester(Semester semester) {
        if (semester != null && !this.semesterSet.contains(semester)) {
            this.semesterSet.add(semester);
            // Only add this teacher to the semester's teacherSet if it's not already present, else stackoverflow will happen
            if (!semester.getTeacherSet().contains(this)) {
                semester.getTeacherSet().add(this);
            }
        }
    }


    public void getSemester(Semester semester) {

    }
}
