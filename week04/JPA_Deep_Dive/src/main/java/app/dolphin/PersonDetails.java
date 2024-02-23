package app.dolphin;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "persondetails")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class PersonDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "zip", nullable = false)
    private int zip;
    @Column(name = "age", nullable = false)
    private int age;
    @OneToOne
    @MapsId
    private Person person;

    public PersonDetails(String address, String city, int zip, int age) {
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.age = age;
    }
}