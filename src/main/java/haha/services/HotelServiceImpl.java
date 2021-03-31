package haha.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import haha.entities.Hotel;
import haha.repositories.HotelRepository;

@Component("hotelService")
@Transactional
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<Hotel> getAllHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel findById(Long id) {
        return hotelRepository.findById(id).get();
    }

}
