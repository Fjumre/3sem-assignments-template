package app.handler;

import app.model.Hotel;
import app.model.Room;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.Set;

public class RoomHandler {


    private Set<Room> rooms;

    public RoomHandler(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public void getAllRooms(Context ctx) {

        ctx.json(rooms);
    }

    public void getRoomById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Room room = rooms.stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundResponse("Room not found"));
        ctx.json(room);
    }

    public void addRoom(Context ctx){

        Room newRoom = ctx.bodyAsClass(Room.class);

        rooms.add(newRoom);

        ctx.status(201).json(newRoom);

    }

    public void updateRoom(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));

        Room roomUpdate = rooms.stream()
                .filter(room -> room.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundResponse("Hotel not found"));

        Room updatedInfo = ctx.bodyAsClass(Room.class);

        // Update the hotelUpdate object with information from updatedInfo
        // This is a simplified example, you'd update each field as necessary
        roomUpdate.setHotelId(updatedInfo.getHotelId());
        roomUpdate.setNumber(updatedInfo.getNumber());
        roomUpdate.setPrice(updatedInfo.getPrice());

        ctx.status(200).json(roomUpdate);
    }

    public void deleteRoom(Context ctx){

        int id = Integer.parseInt(ctx.pathParam("id"));

        boolean removedRoom = rooms.removeIf(room -> room.getId() == id);

        ctx.json(removedRoom + " Room removed");
    }
}
