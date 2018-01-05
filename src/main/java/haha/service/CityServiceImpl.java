package haha.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import haha.entities.City;
import haha.repository.CityRepository;

@Component("cityService")
@Transactional
class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;


    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> getAllCity() {
        return cityRepository.findAll();
    }
}
