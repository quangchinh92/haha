package haha.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import haha.entities.Hotel;
import haha.repositories.HotelRepository;

@Component("hotelService")
@Transactional
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public List<Hotel> getAllHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel findById(Long id) {
        return hotelRepository.findById(id).get();
    }

    @Override
    public List<Hotel> getAllHotelByDeleted(Integer deleted) {
        return hotelRepository.findAllByDeleted(deleted);
    }

    @Override
    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

}
