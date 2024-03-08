package app.functionality;

import app.dto.HotelDTO;
import app.dto.RoomDTO;
import app.model.Hotel;
import app.model.Room;

import java.util.Set;
import java.util.stream.Collectors;

public class HotelFunctionality {

    private RoomFunctionality roomFunctionality = new RoomFunctionality();
    public HotelDTO toHotelDTO(Hotel hotel){

        HotelDTO hotelDTO= new HotelDTO();
        hotelDTO.setId(hotel.getId());
        hotelDTO.setName(hotel.getName());
        hotelDTO.setAddress(hotel.getAddress());
        Set<RoomDTO> roomDTOs = hotel.getRooms().stream()
                .map(room -> roomFunctionality.toRoomDTO(room))
                .collect(Collectors.toSet());

        hotelDTO.setRooms(roomDTOs);

        return hotelDTO;
    }



    public Hotel toHotel(HotelDTO hotelDTO){

        Hotel hotel= new Hotel();
        hotel.setId(hotelDTO.getId());
        hotel.setName(hotelDTO.getName());
        hotel.setAddress(hotelDTO.getAddress());


        return hotel;
    }
}
