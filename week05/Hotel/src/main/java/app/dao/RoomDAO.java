package app.dao;

import app.model.Hotel;
import app.model.Room;
import java.util.ArrayList;

public class RoomDAO extends DAO<Room> {

    public RoomDAO() {
        this.db = new ArrayList<>();
    }

    @Override
    protected boolean matchesId(Room room, int id) {
        return room.getId() == id;
    }

    @Override
    public void create(Hotel hotel) {

    }

    @Override
    protected int getId(Room entity) {
        return entity.getId();
    }

    @Override
    public void create(Room room) {
        db.add(room);
    }
}
