package haha.services;

import java.util.List;

import haha.entities.Hotel;

public interface HotelService {

    List<Hotel> getAllHotel();

    Hotel findById(Long Id);
}
