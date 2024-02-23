package app.GLS;

import jakarta.persistence.*;
import lombok.*;

@ToString
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private long id;
    @Column(name = "latitude", nullable = false)
    private Double Latitude;
    @Column(name = "longitude", nullable = false)
    private Double Longitude;
    @Column(name = "address", nullable = false)
    private String Address;

    public Location(Double latitude, Double longitude, String address) {
        Latitude = latitude;
        Longitude = longitude;
        Address = address;
    }
}
