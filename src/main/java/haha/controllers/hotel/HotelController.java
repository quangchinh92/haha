package haha.controllers.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import haha.entities.Hotel;
import haha.enums.DELETED;
import haha.services.HotelService;
import haha.wrapper.ResponseBodyWrapper;

@RestController
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @RequestMapping("/hotels")
    public ResponseEntity<ResponseBodyWrapper<Hotel>> getHotelList() {
        return ResponseEntity
                .ok(ResponseBodyWrapper.createSuccess(hotelService.getAllHotelByDeleted(DELETED.NO.getValue())));
    }
}
