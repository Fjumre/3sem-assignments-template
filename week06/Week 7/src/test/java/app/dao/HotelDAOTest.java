package app.dao;


import app.model.Hotel;
import app.model.Room;
import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HotelDAOTest {

    private static HotelDAO hotelDAO;

    @BeforeAll
    static void setupAll(){



    }


    @AfterAll
    static void closeAll(){


    }


    @BeforeEach
    void setUp() {
        hotelDAO = new HotelDAO();
        hotelDAO.create(new Hotel(1, "Hotel A", "123 Main St", new HashSet<>()));
        hotelDAO.create(new Hotel(2, "Hotel B", "456 Main St", new HashSet<>()));

    }

    @AfterEach
    void tearDown() {


    }

    @Test
    void getAll() {

        List<Hotel> result = hotelDAO.getAll();
        assertFalse(result.isEmpty(), "Result should not be empty");
        assertEquals(2, result.size(), "Result list should contain one hotel");
        System.out.println(result);

    }

    @Test
    void getById() {

        // When
        int setId = 1;

        // Given
        Hotel hotel = hotelDAO.getById(setId);

        //Then
        assertNotNull(hotel);
        assertEquals(setId, hotel.getId());
        System.out.println(hotel);
    }

    @Test
    void create() {
        Hotel hotel = new Hotel(3, "Saved Hotel", "789 Test", new HashSet<Room>());
        Hotel savedHotel = hotelDAO.create(hotel);
        assertNotNull(savedHotel);
        assertEquals("Saved Hotel", savedHotel.getName());
        System.out.println("Test Hotel " + savedHotel);
    }

    @Test
    void update() {
        Hotel hotel = new Hotel(1, "Updated Hotel", "123 Test St", new HashSet<>());
        Hotel updatedHotel = hotelDAO.update(hotel);
        assertNotNull(updatedHotel, "Hotel should not be null after update.");
        assertEquals("Updated Hotel", updatedHotel.getName());
        System.out.println(updatedHotel);

    }

    @Test
    void delete() {
        hotelDAO.delete(1);
        assertNull(hotelDAO.getById(1));
        System.out.println(hotelDAO.getAll());
    }

    @Test
    void getRoomsByHotelId() {
    }
}