package app.jpqlExercise;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "employee")
@NoArgsConstructor

@NamedQuery(name = "Employee.findAll", query= "SELECT e from Employee e")

public class Employee {


    @Id // Marks the id field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures the way ID is generated
    @Column(name="id", nullable = false, unique = true)
    long id;
    @Column(name = "first_name", nullable = false)
    String first_name;
    @Column(name = "last_name", nullable = false)
    String last_name;
    @Column(name = "email", nullable = false, unique = true)
    String email;
    @Column(name = "salary", nullable = false)
    double salary;
    @Column(name = "department", nullable = false)
    String department;



    public Employee(String first_name, String last_name, String email, double salary, String department) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.salary = salary;
        this.department = department;
    }

}
