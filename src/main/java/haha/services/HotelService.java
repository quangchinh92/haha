package haha.services;

import java.util.List;

import haha.entities.Hotel;

public interface HotelService {

    List<Hotel> getAllHotel();

    List<Hotel> getAllHotelByDeleted(Integer deleted);

    Hotel findById(Long Id);

    Hotel save(Hotel hotel);
}
