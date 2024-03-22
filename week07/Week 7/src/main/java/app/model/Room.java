package app.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
@Entity
@Table(name = "room")

public class Room {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", nullable = false, unique = true)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotelId;

    @Column(name = "number")
    private int number;

    @Column(name = "price")
    private double price;

    public Room(int id, int number, double price) {
        this.id = id;
        this.number = number;
        this.price = price;
    }

    public Room(int number, double price) {
        this.number = number;
        this.price = price;
    }
}
