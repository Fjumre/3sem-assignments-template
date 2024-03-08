package app.handler;

import app.model.Hotel;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.List;

public class HotelHandler {


    private List<Hotel> hotelList;

    public HotelHandler(List<Hotel> hotelList) {
        this.hotelList = hotelList;
    }

    public void getAllHotels(Context ctx) {

        ctx.json(hotelList);
    }

    public void getHotelById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Hotel hotel = hotelList.stream()
                .filter(h -> h.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundResponse("Hotel not found"));
        ctx.json(hotel);
    }

    public void addHotel(Context ctx){

        Hotel newHotel = ctx.bodyAsClass(Hotel.class);

        hotelList.add(newHotel);

        ctx.status(201).json(newHotel);

    }

    public void updateHotel(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));

        Hotel hotelUpdate = hotelList.stream()
                .filter(hotel -> hotel.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundResponse("Hotel not found"));

         Hotel updatedInfo = ctx.bodyAsClass(Hotel.class);

        // Update the hotelUpdate object with information from updatedInfo
        // This is a simplified example, you'd update each field as necessary
        hotelUpdate.setName(updatedInfo.getName());
        hotelUpdate.setAddress(updatedInfo.getAddress());
        hotelUpdate.setRoomSet(updatedInfo.getRoomSet());

        ctx.status(200).json(hotelUpdate);
    }

    public void deleteHotel(Context ctx){

        int id = Integer.parseInt(ctx.pathParam("id"));

        boolean removedHotel = hotelList.removeIf(hotel -> hotel.getId() == id);

        ctx.json(removedHotel + " Hotel removed");
    }
}
