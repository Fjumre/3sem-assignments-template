package app.dto;

import app.model.Hotel;
import app.model.Room;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RoomDTO {


    private int id;

    private int hotelId;

    private int number;

    private double price;
    public RoomDTO(Room room) {
        this.id = room.getId();
        this.hotelId = room.getHotelId().getId(); // Assuming there's a getHotelId() in Room that returns a Hotel entity
        this.number = room.getNumber();
        this.price = room.getPrice();
    }
    public RoomDTO(int number, double price) {
        this.number = number;
        this.price = price;
    }
}
