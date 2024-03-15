package app.dto;


import app.model.Hotel;
import app.model.Room;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor

@EqualsAndHashCode
public class HotelDTO {

    private int id;


    private String name;


    private String address;


    private Set<RoomDTO> roomDTOSet;

    private static final List<app.model.Hotel> hotelList = new ArrayList<>();


//    public HotelDTO(Hotel hotel) {
//        this.id = hotel.getId();
//        this.name = hotel.getName();
//        this.address = hotel.getAddress();
//        this.roomDTOSet = hotel.getRooms().stream()
//                .map(room -> new RoomDTO(room))
//                .collect(Collectors.toSet());
//    }

    public HotelDTO(HotelDTO postHotel) {
        this.id= postHotel.getId();
         this.name= postHotel.getName();
        this.address= postHotel.getAddress();

    }

    public HotelDTO(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public HotelDTO(int id, String name, String address, Set<RoomDTO> roomDTOSet) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.roomDTOSet = roomDTOSet;
    }

    public HotelDTO(Hotel postHotel) {
    }



    public static Optional<app.model.Hotel> findById(int hotelId) {
        return hotelList.stream()
                .filter(hotel -> hotel.getId() == hotelId)
                .findFirst();
    }

    public void setRooms(Set<RoomDTO> roomDTOs) {
        this.roomDTOSet = roomDTOs;
    }
    public Set<app.model.Room> getRoomSet(Set<app.model.Room> roomSet) {

        return roomSet;
    }

    public Set<RoomDTO> getRooms() {
        return roomDTOSet;
    }
}
