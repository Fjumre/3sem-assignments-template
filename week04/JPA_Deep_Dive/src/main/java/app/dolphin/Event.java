package app.dolphin;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "event")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Event {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", nullable = false, unique = true)
    private int Id;

    @Column(name = "eventname", nullable = false)
    private String name;

    @Column(name = "eventdate", nullable = false)
    private LocalDate eventDate;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Set<PersonEvent> personSet= new HashSet<>();


    public Event(String name, LocalDate eventDate) {
        this.name = name;
        this.eventDate = eventDate;
    }


}
