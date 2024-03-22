package app;



import app.config.ApplicationConfig;
import app.config.HibernateConfig;
import app.controllers.ISecurityController;
import app.dto.HotelDTO;
import app.dto.RoomDTO;
import app.model.Room;
import app.handler.HotelHandler;
import app.handler.RoomHandler;
import app.model.Hotel;
import com.fasterxml.jackson.databind.ObjectMapper;
//import app.security.EndPoint;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.security.RouteRole;
import jakarta.persistence.EntityManagerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import app.controllers.SecurityController;
//import static app.security.EndPoint.*;
import static io.javalin.apibuilder.ApiBuilder.*;


public class Main {

    private static ISecurityController securityController = new SecurityController();
    private static ObjectMapper om = new ObjectMapper();

    private static RoomDTO room1 = new RoomDTO(101, 150.0);
    private static RoomDTO room2 = new RoomDTO(102, 200.0);
    private static RoomDTO room3 = new RoomDTO(103, 250.0);
    private static RoomDTO room4 = new RoomDTO(104, 300.0);
    private static RoomDTO room5 = new RoomDTO(105, 350.0);

    private static Set<RoomDTO> rooms1 = new HashSet<>();
    private static Set<RoomDTO> rooms2 = new HashSet<>();
    private static Set<RoomDTO> rooms3 = new HashSet<>();
    private static Set<RoomDTO> rooms4 = new HashSet<>();
    private static Set<RoomDTO> rooms5 = new HashSet<>();

    private static HotelDTO hotel1 = new HotelDTO(1, "HotelDTO One", "123 Main St", rooms1);
    private static HotelDTO hotel2 = new HotelDTO(2, "HotelDTO Two", "456 Main St", rooms2);
    private static HotelDTO hotel3 = new HotelDTO(3, "HotelDTO Three", "789 Main St", rooms3);
    private static HotelDTO hotel4 = new HotelDTO(4, "HotelDTO Four", "101 Main St", rooms4);
    private static HotelDTO hotel5 = new HotelDTO(5, "HotelDTO Five", "202 Main St", rooms5);

    static List<HotelDTO> hotelList = List.of(hotel1, hotel2, hotel3, hotel4, hotel5);

    static Set<RoomDTO> rooms = new HashSet<>();

    public static HotelHandler hotelHandler = new HotelHandler(hotelList);
    public static RoomHandler roomHandler = new RoomHandler(rooms);

    static {
        // Initialize room sets
        rooms1.add(room1);
        rooms2.add(room2);
        rooms3.add(room3);
        rooms4.add(room4);
        rooms5.add(room5);

        // Combine all rooms into a single set
        rooms.addAll(rooms1);
        rooms.addAll(rooms2);
        rooms.addAll(rooms3);
        rooms.addAll(rooms4);
        rooms.addAll(rooms5);
    }
    public static void main(String[] args) {

        startServer(7007);

    }

    public static void startServer(int port){

        ObjectMapper om = new ObjectMapper();
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        ApplicationConfig applicationConfig = ApplicationConfig.getInstance();
        applicationConfig
                .initiateServer()
                .startServer(7007)
                .setExceptionHandling()
                .setRoute(getSecurityRoutes())
                .setupAccessManager()
                .setRoute(getSecuredRoutes())
                .setRoute(() -> {

                getRoute();

                })
                .checkSecurityRoles();
    }


    public static void closeServer () {
        ApplicationConfig.getInstance().stopServer();
    }


    public static EndpointGroup getSecurityRoutes() {
        return ()->{
            path("/auth", ()->{
                post("/login", securityController.login(),Role.ANYONE);
                post("/register", securityController.register(),Role.ANYONE);
            });
        };
    }

    public static EndpointGroup getSecuredRoutes(){
        return ()->{
            path("/protected", ()->{
                before(securityController.authenticate());
                get("/user_demo",(ctx)->ctx.json(om.createObjectNode().put("msg",  "Hello from USER Protected")),Role.USER);
                get("/admin_demo",(ctx)->ctx.json(om.createObjectNode().put("msg",  "Hello from ADMIN Protected")),Role.ADMIN);
            });
        };
    }

    public static void getRoute(){
        before(securityController.authenticate());
        path("vacation", () -> {
            path("/hotels", () -> {
                before(securityController.authenticate());
                get("/", hotelHandler::getAllHotels, Role.USER);
                get("/{id}", hotelHandler::getHotelById, Role.ADMIN);
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
    }

    public enum Role implements RouteRole { ANYONE, USER, ADMIN }
}


