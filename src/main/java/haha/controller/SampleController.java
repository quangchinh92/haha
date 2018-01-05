package haha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import haha.entities.City;
import haha.entities.Hotel;
import haha.entities.Review;
import haha.service.CityService;
import haha.service.HotelService;
import haha.service.ReviewService;

@RestController
public class SampleController {
    @Autowired
    private CityService cityService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/cities")
    public List<City> getCityList() {
        return this.cityService.getAllCity();
    }

    @GetMapping("/hotels")
    public List<Hotel> getHotelList() {
        return this.hotelService.getAllHotel();
    }

    @GetMapping("/reviews")
    public List<Review> getReviewList() {
        return this.reviewService.getAll();
    }
}
