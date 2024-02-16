package app.unicorns;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "unicorn")
@NoArgsConstructor
public class Unicorn {

    @Id // Marks the id field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures the way ID is generated
    int id;
    String name;
    int age;
    double powerStrength;


    public Unicorn(String name, int age, double powerStrength) {
        this.name = name;
        this.age = age;
        this.powerStrength = powerStrength;
    }

}
