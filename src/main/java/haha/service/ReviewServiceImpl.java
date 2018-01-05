package haha.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import haha.entities.Review;
import haha.repository.ReviewRepository;

@Component("reviewService")
@Transactional
class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;


    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }
}
