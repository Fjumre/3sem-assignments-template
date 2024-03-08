package app;



import app.config.ApplicationConfig;
import app.config.HibernateConfig;
import app.handler.HotelHandler;
import app.handler.RoomHandler;
import app.model.Hotel;
import app.model.Room;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManagerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.javalin.apibuilder.ApiBuilder.*;


public class Main {
    public static void main(String[] args) {

        Room room1 = new Room(101, 150.0);
        Room room2 = new Room(102, 200.0);
        Room room3 = new Room(103, 250.0);
        Room room4 = new Room(104, 300.0);
        Room room5 = new Room(105, 350.0);

        // Create a set of rooms for the first hotel
        Set<Room> rooms1 = new HashSet<>();
        rooms1.add(room1);

        Set<Room> rooms2 = new HashSet<>();
        rooms2.add(room2);

        Set<Room> rooms3 = new HashSet<>();
        rooms3.add(room3);

        Set<Room> rooms4 = new HashSet<>();
        rooms4.add(room4);

        Set<Room> rooms5 = new HashSet<>();
        rooms5.add(room5);


        Hotel hotel1 = new Hotel(1, "Hotel One", "123 Main St", rooms1);
        Hotel hotel2 = new Hotel(2, "Hotel Two", "456 Main St", rooms2);
        Hotel hotel3 = new Hotel(3, "Hotel Three", "789 Main St", rooms3);
        Hotel hotel4 = new Hotel(4, "Hotel Four", "101 Main St", rooms4);
        Hotel hotel5 = new Hotel(5, "Hotel Five", "202 Main St", rooms5);


        List<Hotel> hotelList = List.of(hotel1, hotel2, hotel3, hotel4, hotel5);

        Set<Room> rooms = new HashSet<>();
        rooms.addAll(rooms1);
        rooms.addAll(rooms2);
        rooms.addAll(rooms3);
        rooms.addAll(rooms4);
        rooms.addAll(rooms5);

        HotelHandler hotelHandler= new HotelHandler(hotelList);
        RoomHandler roomHandler= new RoomHandler(rooms);
        ObjectMapper om = new ObjectMapper();
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory(false);
        ApplicationConfig applicationConfig = ApplicationConfig.getInstance();
        applicationConfig
                .initiateServer()
                .startServer(7007)
                .setExceptionHandling()
                .setRoute(() -> {

                    path("vacation", () -> {
                        path("/hotels", () -> {
                                get("/", hotelHandler::getAllHotels);
                                get("/{id}", hotelHandler::getHotelById);
                                post("/add", hotelHandler::addHotel);
                                put("/update/{id}", hotelHandler::updateHotel);
                                delete("/delete/{id}", hotelHandler::deleteHotel);
                                get("/error", ctx -> {
                                    throw new Exception(String.valueOf(ApplicationConfig.getInstance().setExceptionHandling()));
                                });
                            });
                            path("/rooms", () -> {
                                get("/", roomHandler::getAllRooms);
                                get("/{id}",roomHandler::getRoomById);
                                post("/add", roomHandler::addRoom);
                                put("/update/{id}", roomHandler::updateRoom);
                                delete("/delete/{id}", roomHandler::deleteRoom);
                                get("/error", ctx -> {
                                    throw new Exception(String.valueOf(ApplicationConfig.getInstance().setExceptionHandling()));
                                });
                            });

                        });
                    });
    }
}

