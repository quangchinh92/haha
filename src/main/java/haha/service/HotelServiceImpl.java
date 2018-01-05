package haha.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import haha.entities.Hotel;
import haha.repository.HotelRepository;

@Component("hotelService")
@Transactional
class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<Hotel> getAllHotel() {
        return this.hotelRepository.findAll();
    }
}
