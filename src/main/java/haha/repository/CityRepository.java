package haha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import haha.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findAll();
}
