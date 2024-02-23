package app.dolphin;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "note")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", nullable = false, unique = true)
    private int id;

    @Column(name = "infonote", nullable = false)
    private String infonote;

    @Column(name = "notedate", nullable = false)
    private LocalDate noteDate;

    @Column(name = "createdby", nullable = false)
    private String createdBy;

    @ManyToOne
    private Person person;

    public Note(String infonote, LocalDate noteDate, String createdBy) {
        this.infonote = infonote;
        this.noteDate = noteDate;
        this.createdBy = createdBy;
    }
}
