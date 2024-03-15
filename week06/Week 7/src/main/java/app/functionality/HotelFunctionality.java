package app.functionality;

import app.dto.HotelDTO;
import app.dto.RoomDTO;
import app.model.Hotel;
import java.util.Set;
import java.util.stream.Collectors;

public class HotelFunctionality {

    private RoomFunctionality roomFunctionality = new RoomFunctionality();

    // Converts a Hotel entity to a HotelDTO
    public HotelDTO toHotelDTO(Hotel hotel) {
        // Assuming HotelDTO has a constructor that accepts these parameters directly
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(hotel.getId());
        hotelDTO.setName(hotel.getName());
        hotelDTO.setAddress(hotel.getAddress());
        // Convert each Room entity associated with the Hotel to a RoomDTO
        Set<RoomDTO> roomDTOs = hotel.getRooms().stream()
                .map(room -> roomFunctionality.toRoomDTO(room))
                .collect(Collectors.toSet());
        hotelDTO.setRooms(roomDTOs);
        return hotelDTO; // Corrected return statement
    }

    // Converts a HotelDTO to a Hotel entity
    public Hotel toHotel(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setId(hotelDTO.getId()); // Note: Be cautious about setting ID directly like this for entities managed by ORM
        hotel.setName(hotelDTO.getName());
        hotel.setAddress(hotelDTO.getAddress());
        // Note: Conversion of RoomDTOs back to Room entities might be necessary if they're included in HotelDTO
        // This would require setting up and linking Room entities to the Hotel entity being returned
        return hotel;
    }
}
