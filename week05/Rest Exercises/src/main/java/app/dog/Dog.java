package app.dog;

import app.dog.dto.DogDTO;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Dog {

private long id;
private String name;
private String owner;
private int ownerPhoneNumber;
SpayedOrNeutered spayedOrNeutered;
DogDTO dogDTO;

    public Dog(long id, String name, String owner, int ownerPhoneNumber, SpayedOrNeutered spayedOrNeutered) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.ownerPhoneNumber = ownerPhoneNumber;
        this.spayedOrNeutered = spayedOrNeutered;
    }

    public Dog(String name, String owner, int ownerPhoneNumber, SpayedOrNeutered spayedOrNeutered) {
        this.name = name;
        this.owner = owner;
        this.ownerPhoneNumber = ownerPhoneNumber;
        this.spayedOrNeutered = spayedOrNeutered;
    }

    public enum SpayedOrNeutered{

    Yes,
    No
}
}
