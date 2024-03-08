package app.dao;

import app.model.Hotel;
import app.model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class DAO<T> implements IDAO<T> {

    protected List<T> db = new ArrayList<>();

    @Override
    public List<T> getAll() {
        return new ArrayList<>(db);
    }

    @Override
    public T getById(int id) {
        return db.stream()
                .filter(entity -> getId(entity) == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void create(T entity) {
        db.add(entity);
    }

    @Override
    public T update(T entity) {
        Optional<T> existing = db.stream()
                .filter(e -> getId(e) == getId(entity))
                .findFirst();
        existing.ifPresent(e -> {
            int index = db.indexOf(e);
            db.set(index, entity);
        });
        return entity;
    }

    @Override
    public void delete(int id) {
        db.removeIf(entity -> getId(entity) == id);
    }

    protected abstract boolean matchesId(Room room, int id);

    public abstract void create(Hotel hotel);

    protected abstract int getId(T entity);
}
