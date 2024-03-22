package app.handler;

import app.dto.RoomDTO;
import app.model.Room;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.Set;

public class RoomHandler {


    private Set<RoomDTO> rooms;

    public RoomHandler(Set<RoomDTO> rooms) {
        this.rooms = rooms;
    }


    public void getAllRooms(Context ctx) {

        ctx.json(rooms);
    }

    public void getRoomById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        RoomDTO room = rooms.stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundResponse("Room not found"));
        ctx.json(room);
    }

    public void addRoom(Context ctx){

        RoomDTO newRoom = ctx.bodyAsClass(RoomDTO.class);

        rooms.add(newRoom);

        ctx.status(201).json(newRoom);

    }

    public void updateRoom(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));

        RoomDTO roomUpdate = rooms.stream()
                .filter(room -> room.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundResponse("HotelDTO not found"));

        RoomDTO updatedInfo = ctx.bodyAsClass(RoomDTO.class);

        // Update the hotelUpdate object with information from updatedInfo
        // This is a simplified example, you'd update each field as necessary
        roomUpdate.setHotelId(updatedInfo.getHotelId());
        roomUpdate.setNumber(updatedInfo.getNumber());
        roomUpdate.setPrice(updatedInfo.getPrice());

        ctx.status(200).json(roomUpdate);
    }

    public void deleteRoom(Context ctx){

        int id = Integer.parseInt(ctx.pathParam("id"));

        boolean removedRoom = rooms.removeIf(RoomDTO -> RoomDTO.getId() == id);

        ctx.json(removedRoom + " Room removed");
    }
}
