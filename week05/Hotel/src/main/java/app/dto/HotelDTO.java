package app.dto;


import app.model.Hotel;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class HotelDTO {

    private int id;


    private String name;


    private String address;


    private Set<RoomDTO> roomDTOSet;

    private static final List<Hotel> hotelList = new ArrayList<>();

    public static Optional<Hotel> findById(int hotelId) {
        return hotelList.stream()
                .filter(hotel -> hotel.getId() == hotelId)
                .findFirst();
    }
    public Set<RoomDTO> setRooms(Set<RoomDTO> roomDTOs) {

        return roomDTOSet;
    }
}
