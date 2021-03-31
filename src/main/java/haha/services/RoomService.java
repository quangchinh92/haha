package haha.services;

import java.util.List;

import haha.entities.Room;

public interface RoomService {
    List<Room> getAllRoom();

    Room insert(Room room);

    Room findById(Long id);

    Room findByIdAndHotelId(Long id, Long hotelId);

    List<Room> findByHotelId(Long hotelId);

    Room update(Long id, Room room);

    void delete(Long id);
}
