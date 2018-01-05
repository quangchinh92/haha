package haha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import haha.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAll();

}
