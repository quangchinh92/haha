package haha.services;

import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import haha.entities.City;
import haha.entities.Hotel;
import haha.entities.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelServiceImplTest {

    @Autowired
    private HotelService hotelService;

    @Test
    @Transactional
    public void testSave() {
        Hotel hotel = new Hotel();
        hotel.setName("Hotel Vũng Tàu 1");
        City city = new City();
        city.setId(1L);
        hotel.setCity(city);
        User user = new User();
        user.setId(1L);
        hotel.getCity();
        hotel.setAddress("Bãi Trước");
        hotel.setDeleted(0);
        hotel.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        hotel.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        hotel = hotelService.save(hotel);
        Hotel result = hotelService.findById(hotel.getId());
        System.out.println(result.getId());
    }

}
