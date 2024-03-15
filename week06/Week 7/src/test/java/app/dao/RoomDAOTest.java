package app.dao;

import app.model.Hotel;
import app.model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomDAOTest {

    private RoomDAO roomDAO;
    private HotelDAO hotelDAO;
    private Hotel hotel;

    @BeforeEach
    void setUp() {
        hotel= new Hotel();
        hotelDAO= new HotelDAO();
        roomDAO= new RoomDAO();
        hotelDAO.create(new Hotel(1, "Hotel A", "123 Main St"));
        Hotel hotel1 = hotelDAO.getById(1);
        roomDAO.create(new Room(10, hotel1, 101, 522));
        roomDAO.create(new Room(20, new Hotel(2, "Hotel B", "456 Main St"),202, 299));

    }

    @Test
    void matchesId() {


        Room room= roomDAO.getById(10);

        assertEquals(1, room.getHotelId().getId());


    }

    @Test
    void create() {
        Room newRoom = new Room(30,new Hotel(3), 333, 999.0);
        roomDAO.create(newRoom);
        Room retrievedRoom = roomDAO.getById(30);

        assertNotNull(retrievedRoom, "Room should not be null");
        assertEquals(30, retrievedRoom.getId());
        System.out.println(retrievedRoom);
    }

    @Test
    void getId() {

        Room room = roomDAO.getById(10);
        assertNotNull(room);
        assertEquals(10, room.getId());
        System.out.println(room);
    }

}