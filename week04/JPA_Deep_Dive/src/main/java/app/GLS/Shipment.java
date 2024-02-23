package app.GLS;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@ToString
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "shipment")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private Package aPackage;

    @ManyToOne
    @JoinColumn(name = "source_location_id")
    private Location sourceLocation;

    @ManyToOne
    @JoinColumn(name = "destination_location_id")
    private Location destinationLocation;

    @Column(name = "shipmentdate")
    private LocalDate shipmentDate;

    public Shipment(Package aPackage, Location sourceLocation, Location destinationLocation, LocalDate shipmentDate) {
        this.aPackage = aPackage;
        this.sourceLocation = sourceLocation;
        this.destinationLocation = destinationLocation;
        this.shipmentDate = shipmentDate;
    }
}
