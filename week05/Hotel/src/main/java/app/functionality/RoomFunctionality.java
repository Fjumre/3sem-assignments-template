package app.functionality;

import app.dto.HotelDTO;
import app.dto.RoomDTO;
import app.model.Hotel;
import app.model.Room;

public class RoomFunctionality {


    public RoomDTO toRoomDTO(Room room){
        RoomDTO roomDTO= new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setHotelId(room.getHotelId().getId());
        roomDTO.setNumber(room.getNumber());
        roomDTO.setPrice(room.getPrice());

        return roomDTO;
    }

    public Room toRoom(RoomDTO roomDTO){
        Room room= new Room();
        room.setId(roomDTO.getId());
        Hotel hotel = HotelDTO.findById(roomDTO.getHotelId()).orElse(null);
        room.setHotelId(hotel);
        room.setNumber(roomDTO.getNumber());
        room.setPrice(roomDTO.getPrice());


        return room;
    }
}
