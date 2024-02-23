package app.jpa_rm_jpql;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "semester")
@NoArgsConstructor
@AllArgsConstructor
public class Semester {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    private int id;
    @Column(name = "semester_name", nullable = false)
    private String semester_name;


    @OneToMany(mappedBy = "semester", cascade = CascadeType.PERSIST)
    private Set<Student> studentSet = new HashSet<>();

    @ManyToMany(mappedBy = "semesterSet")
    private Set<Teacher> teacherSet = new HashSet<>();

    public Semester(String semester_name) {

        this.semester_name=semester_name;
    }


    public void addTeacher(Teacher teacher) {
        if (teacher != null && !this.teacherSet.contains(teacher)) {
            this.teacherSet.add(teacher);
            // Only add this semester to the teacher's semesterSet if it's not already present, else stackoverflow will happen
            if (!teacher.getSemesterSet().contains(this)) {
                teacher.getSemesterSet().add(this);
            }
        }
    }

    public void addStudent(Student student){
        if (student!= null){
            studentSet.add(student);
            student.setSemester(this);
        }
    }
}


