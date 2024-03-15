package app.dao;

import app.model.Room;

public class RoomDAO extends DAO<Room> {

    public RoomDAO() {
        super(); // Use the DAO<T> constructor
    }
    @Override
    protected boolean matchesId(Room room, int id) {

        return room.getId() == id;
    }

    @Override
    public Room create(Room room) {


        return room;
    }

    @Override
    protected int getId(Room entity) {
        return entity.getId();
    }

}
