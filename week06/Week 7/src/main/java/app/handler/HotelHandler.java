package app.handler;

import app.dto.HotelDTO;
import app.functionality.HotelFunctionality;
import app.model.Hotel;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.List;

public class HotelHandler {


    private static ObjectMapper om = new ObjectMapper();

    private HotelFunctionality hotelFunctionality = new HotelFunctionality();
    private List<HotelDTO> hotelList;
    public HotelHandler(List<HotelDTO> hotelList) {
        this.hotelList = hotelList;
    }

    public void getAllHotels(Context ctx) {

        ctx.json(hotelList);
    }

    public void getHotelById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        HotelDTO hotel = hotelList.stream()
                .filter(h -> h.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundResponse("HotelDTO not found"));
        ctx.json(hotel);
    }

    public void addHotel(Context ctx) {
        // Parse the request body to get a HotelDTO object
        Hotel newHotel = ctx.bodyAsClass(Hotel.class);

        // Use HotelFunctionality to convert HotelDTO to Hotel entity
        HotelDTO newHotelDTO = hotelFunctionality.toHotelDTO(newHotel);

        // Here you would save newHotel to your database
        // For demonstration, we're adding it to an in-memory list
        hotelList.add(newHotelDTO);

        // Optionally, convert back to DTO to return the created entity
       // HotelDTO createdHotelDTO = hotelFunctionality.toHotelDTO(newHotel);

        // Return the newly added hotel as JSON with status 201
        ctx.status(201).json(newHotelDTO);
    }
//    public HotelDTO addHotel(HotelDTO hotelDTO) {
//        Hotel hotel = hotelFunctionality.toHotel(hotelDTO);
//        Hotel savedHotel = hotelList.add(hotel); // Save the hotel entity to the database
//        return hotelFunctionality.toHotelDTO(savedHotel); // Convert back to DTO to return
//    }
public void updateHotel(Context ctx) {
    int id = Integer.parseInt(ctx.pathParam("id"));
    HotelDTO hotelDTO = ctx.bodyAsClass(HotelDTO.class); // Directly work with HotelDTO

    // Optional: Validate the hotelDTO here

    HotelDTO updatedHotel = hotelList.stream()
            .filter(hotel -> hotel.getId() == id)
            .findFirst()
            .orElseThrow(() -> new NotFoundResponse("Hotel not found"));

    // Assuming you have setters in your HotelDTO for name, address, etc.
    // Update the hotel with new data from the received DTO
    updatedHotel.setName(hotelDTO.getName());
    updatedHotel.setAddress(hotelDTO.getAddress());
    updatedHotel.setRooms(hotelDTO.getRooms()); // Make sure your DTO has a way to handle rooms

    // Directly return updatedHotel DTO. Assuming you don't need to convert it back to an entity here.
    // If you do need to save this to a database, you would convert this DTO to an entity and persist it
    ctx.status(200).json(updatedHotel);
}
    public void deleteHotel(Context ctx){

        int id = Integer.parseInt(ctx.pathParam("id"));

        boolean removedHotel = hotelList.removeIf(hotel -> hotel.getId() == id);

        ctx.json(removedHotel + " Hotel removed");
    }
}
