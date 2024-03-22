//package app.security;
//
//import app.config.ApplicationConfig;
//import app.controllers.SecurityController;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.javalin.apibuilder.EndpointGroup;
//import io.javalin.security.RouteRole;
//
//import static app.Main.hotelHandler;
//import static app.Main.roomHandler;
//import static io.javalin.apibuilder.ApiBuilder.*;
//
//public class EndPoint {
//    private static ObjectMapper om = new ObjectMapper();
//
//    private static SecurityController securityController;
//    public static EndpointGroup getSecurityRoutes() {
//        return ()->{
//            path("/auth", ()->{
//                post("/login", securityController.login(),Role.ANYONE);
//                post("/register", securityController.register(),Role.ANYONE);
//            });
//        };
//    }
//
//    public static EndpointGroup getSecuredRoutes(){
//        return ()->{
//            path("/protected", ()->{
//                before(securityController.authenticate());
//                get("/user_demo",(ctx)->ctx.json(om.createObjectNode().put("msg",  "Hello from USER Protected")),Role.USER);
//                get("/admin_demo",(ctx)->ctx.json(om.createObjectNode().put("msg",  "Hello from ADMIN Protected")),Role.ADMIN);
//            });
//        };
//    }
//
//    public static void getRoute(){
//        path("vacation", () -> {
//            path("/hotels", () -> {
//                get("/", hotelHandler::getAllHotels);
//                get("/{id}", hotelHandler::getHotelById);
//                post("/add", hotelHandler::addHotel);
//                put("/update/{id}", hotelHandler::updateHotel);
//                delete("/delete/{id}", hotelHandler::deleteHotel);
//                get("/error", ctx -> {
//                    throw new Exception(String.valueOf(ApplicationConfig.getInstance().setExceptionHandling()));
//                });
//            });
//            path("/rooms", () -> {
//                get("/", roomHandler::getAllRooms);
//                get("/{id}",roomHandler::getRoomById);
//                post("/add", roomHandler::addRoom);
//                put("/update/{id}", roomHandler::updateRoom);
//                delete("/delete/{id}", roomHandler::deleteRoom);
//                get("/error", ctx -> {
//                    throw new Exception(String.valueOf(ApplicationConfig.getInstance().setExceptionHandling()));
//                });
//            });
//
//        });
//    }
//
//    public enum Role implements RouteRole { ANYONE, USER, ADMIN }
//}
