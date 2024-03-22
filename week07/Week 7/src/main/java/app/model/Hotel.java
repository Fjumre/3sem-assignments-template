package app.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;


@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
@Entity
@Table(name = "hotel")

public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", nullable = false, unique = true)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "hotelId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Room> roomSet;

    public Hotel(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Hotel(int id) {
        this.id = id;
    }

    public Hotel(int id, String name, String address, Set<Room> roomSet) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.roomSet = roomSet;
    }

    public Hotel(Hotel postHotel) {
    }

    public Set<Room> getRooms() {

        return roomSet;
    }
    public void addRoom(Room room) {
        roomSet.add(room);
        room.setHotelId(this);
    }

    public void removeRoom(Room room) {
        roomSet.remove(room);
        room.setHotelId(null);
    }
}

