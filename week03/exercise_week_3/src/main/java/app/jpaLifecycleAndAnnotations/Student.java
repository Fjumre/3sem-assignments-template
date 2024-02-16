package app.jpaLifecycleAndAnnotations;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Entity
@Table(name = "student")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;


    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "age", nullable = false)
    private int age;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Student(String firstName, String lastName, String email, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }
    //@PrePersist it is about time where you can see when a user was created/joined
    //@PreUpdate it is about time when the user last updated their info/account or possible last was active

    @PrePersist
    public void createdAt(){
        this.createdAt= LocalDateTime.now();
        this.updatedAt= LocalDateTime.now();
    }
    @PreUpdate
    public void updatedAt(){
        this.updatedAt= LocalDateTime.now();
    }
    public String getName() {
        return firstName + " " + lastName;
    }

}








