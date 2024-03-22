package app.dao;

import app.model.Hotel;
import app.model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HotelDAO implements IDAO<Hotel> {

    private List<Hotel> hotelList = new ArrayList<>();

    @Override
    public List<Hotel> getAll() {
        return new ArrayList<>(hotelList);
    }

    @Override
    public Hotel getById(int id) {
        return hotelList.stream()
                .filter(hotel -> hotel.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Hotel create(Hotel hotel) {
        hotelList.add(hotel);
        return hotel;
    }

    @Override
    public Hotel update(Hotel hotel) {
        Optional<Hotel> existingHotel = hotelList.stream()
                .filter(h -> h.getId() == hotel.getId())
                .findFirst();
        existingHotel.ifPresent(h -> {
            hotelList.set(hotelList.indexOf(h), hotel);
        });
        return hotel;
    }

    @Override
    public void delete(int id) {
        hotelList.removeIf(hotel -> hotel.getId() == id);
    }

    public List<Room> getRoomsByHotelId(int hotelId) {
        return hotelList.stream()
                .filter(hotel -> hotel.getId() == hotelId)
                .flatMap(hotel -> hotel.getRoomSet().stream())
                .collect(Collectors.toList());
    }

}
