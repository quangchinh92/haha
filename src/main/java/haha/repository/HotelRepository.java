package haha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import haha.entities.City;
import haha.entities.Hotel;
import haha.entities.RatingCount;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    Hotel findByCityAndName(City city, String name);

    @Query("select r.rating as rating, count(r) as count "
            + "from Review r where r.hotel = ?1 group by r.rating order by r.rating DESC")
    List<RatingCount> findRatingCounts(Hotel hotel);

}
