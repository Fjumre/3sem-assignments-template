package app.dolphin;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "personevent")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class PersonEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", nullable = false, unique = true)
    private int id;

    @ManyToOne
    private Person person;

    @ManyToOne
    private Event event;

    @Column(name = "signupdate", nullable = false)
    private LocalDate signUpDate;

    @Column(name = "eventfee", nullable = false)
    private double eventFee;


    public PersonEvent(Person person, Event event, LocalDate signUpDate, double eventFee) {
        this.person = person;
        this.event = event;
        this.signUpDate = signUpDate;
        this.eventFee = eventFee;
    }
}
