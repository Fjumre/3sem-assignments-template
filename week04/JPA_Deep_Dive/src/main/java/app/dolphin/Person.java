package app.dolphin;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Person {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", nullable = false, unique = true)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private PersonDetails personDetails;

    public Person(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<Fee> feeSet= new HashSet<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<Note> noteSet= new HashSet<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<PersonEvent> eventSet= new HashSet<>();

    public void addPersonDetails(PersonDetails personDetails){
        this.personDetails= personDetails;

        if (personDetails!= null){
            personDetails.setPerson(this);
        }
    }

    public void addFee(Fee fee){
        this.feeSet.add(fee);
        if (fee!= null){
            fee.setPerson(this);
        }
    }

    public void addNote(Note note){
        this.noteSet.add(note);
        if (note!= null){
            note.setPerson(this);
        }
    }

    public void addEvent(Person person, Event event, LocalDate signUpDate, double eventFee){

        PersonEvent personEvent= new PersonEvent(person, event, signUpDate, eventFee);
        this.eventSet.add(personEvent);

    }
}
