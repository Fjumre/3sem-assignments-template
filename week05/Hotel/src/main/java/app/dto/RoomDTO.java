package app.dto;

import app.model.Hotel;
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
}
