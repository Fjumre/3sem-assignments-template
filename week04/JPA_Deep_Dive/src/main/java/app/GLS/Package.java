package app.GLS;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@ToString
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "package")
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    long id;

    @Column(name = "trackingNumber", nullable = false)
    String trackingNumber;

    @Column(name = "senderName", nullable = false)
    String senderName;

    @Column(name = "receiverName", nullable = false)
    String receiverName;

    @OneToMany(mappedBy = "aPackage", cascade = CascadeType.PERSIST)
    private Set<Shipment> shipmentSet;

    public void addShipment(Shipment shipment) {
        shipmentSet.add(shipment);
        shipment.setAPackage(this);
    }

    public void removeShipment(Shipment shipment) {
        shipmentSet.remove(shipment);
        shipment.setAPackage(null);
    }
    @Enumerated(EnumType.STRING) // Specifies that the enum value will be stored as a String
    @Column(name = "deliverystatus", nullable = false)
    private DeliveryStatus deliveryStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Package(String trackingNumber, String senderName, String receiverName, DeliveryStatus deliveryStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.trackingNumber = trackingNumber;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.deliveryStatus = deliveryStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Package(String trackingNumber, String senderName, String receiverName, DeliveryStatus deliveryStatus) {
        this.trackingNumber = trackingNumber;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.deliveryStatus = deliveryStatus;
    }


    public enum DeliveryStatus {
        PENDING,
        IN_TRANSIT,
        DELIVERED
    }

    @PrePersist
    public void createdAt(){
        this.createdAt= LocalDateTime.now();
        this.updatedAt= LocalDateTime.now();
    }
    @PreUpdate
    public void updatedAt(){
        this.updatedAt= LocalDateTime.now();
    }
}
