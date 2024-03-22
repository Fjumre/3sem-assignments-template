//package app;
//
//import app.dto.HotelDTO;
//import app.model.Hotel;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import org.junit.jupiter.api.*;
//
//import java.util.HashSet;
//import java.util.List;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.collection.IsEmptyCollection.empty;
//import static org.hamcrest.core.Is.is;
//import static org.hamcrest.core.IsEqual.equalTo;
//import static org.hamcrest.core.IsNot.not;
//
//class HotelResourceTest {
//
//
//    private static ObjectMapper objectMapper= new ObjectMapper();
//
//
//    @BeforeAll
//    static void setupAll(){
//
//        RestAssured.baseURI= "http://localhost:7007/api/";
//        Main.startServer(7007);
//    }
//
//
//    @AfterAll
//    static void closeAll(){
//
//        Main.closeServer();
//
//    }
//
//
//    @BeforeEach
//    void setUp() {
//
//    }
//
//    @AfterEach
//    void tearDown() {
//
//        //Main.hotelList.clear();
//        //Main.rooms.clear();
//
//    }
//
//    @Test
//    @DisplayName("Test server is running")
//    void testServerIsRunning(){
//
//        RestAssured
//                .given()
//                .when()
//                .get("vacation/hotels/")
//                .then()
//                .statusCode(200);
//    }
//
//
//    @Test
//    @DisplayName("Test server error")
//    void testServerError(){
//
//        RestAssured
//                .given()
//                .when()
//                .get("vacation/hotels/error")
//                .then()
//                .statusCode(500);
//    }
//
//    @Test
//    @DisplayName("Test found hotel")
//    void testName(){
//
//        RestAssured
//                .given()
//                .when()
//                .get("vacation/hotels/3")
//                .then()
//                .statusCode(200);
//    }
//
//
//    @Test
//    @DisplayName("Test found hotel list")
//    void testPersonList(){
//
//        List<HotelDTO> list = given()
//                .when()
//                .get("vacation/hotels/")
//                .then()
//                .statusCode(200) // Assert that the status code is 200
//                .extract()
//                .body()
//                .jsonPath().getList("", HotelDTO.class);
//
//        // Extract the list of persons from the response
//        //List<HotelDTO> extractedHotelList = jsonPath.getList("$", HotelDTO.class);
//
//        assertThat(list, is(not(empty())));
//
//        // Print the extracted list for debugging purposes
//        list.forEach(System.out::println);
//    }
//
//
//    @Test
//    @DisplayName("Post example")
//    void testPostToServer() throws JsonProcessingException {
//
//       HotelDTO postHotelDTO= new HotelDTO(12, "Test Hotel", "999 Test St", new HashSet<>());
////        RestAssured
////                .with()
////                .body(new HotelDTO(12, "Test HotelDTO", "999 Test St", new HashSet<>()))
////                .when()
////                .request("POST", "vacation/hotels/add")
////                .then()
////                .statusCode(201);
//
//        //HotelDTO hotel = new HotelDTO(postHotel);
////                String requestBody= objectMapper.writeValueAsString(hotel);
////        System.out.println(requestBody);
//        String requestBody = objectMapper.writeValueAsString(postHotelDTO);
//        System.out.println(requestBody);
//        RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .body(requestBody)
//                .when()
//                .post("/vacation/hotels/add")
//                .then().log().all()
//                .statusCode(201)
//                .body("name", equalTo("Test Hotel"));
//
//    }
//
//    @Test
//    @DisplayName("Put/update example")
//    void testUpdateToServer(){
//
//        HotelDTO updatedHotel = new HotelDTO(1, "Updated HotelDTO", "1 Test St", new HashSet<>());
//
//        given()
//                .contentType("application/json")
//                .body(updatedHotel) // Use the hotel object as the request body
//                .when()
//                .put("/vacation/hotels/{id}", updatedHotel.getId()) // Specify the ID in the URL
//                .then()
//                .statusCode(200);
//
//        System.out.println(Main.hotelList);
//    }
//
//    @Test
//    @DisplayName("Delete/remove example")
//    void testDeleteToServer(){
//
//        int hotelIdToRemove = 1;
//
//        given()
//                .contentType("application/json")
//                .pathParam("id", hotelIdToRemove) // Set the ID as a path parameter
//                .when()
//                .delete("/vacation/hotels/delete/{id}")
//                .then()
//                .statusCode(204); // Expect a 200 OK or 204 No Content for successful deletion
//
//        System.out.println(Main.hotelList);
//    }
//}