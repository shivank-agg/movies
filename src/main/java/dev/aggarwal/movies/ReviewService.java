package dev.aggarwal.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    ReviewRepository reviewRepository;

    public ReviewService(MongoTemplate mongoTemplate, ReviewRepository reviewRepository) {
        this.mongoTemplate = mongoTemplate;
        this.reviewRepository = reviewRepository;
    }

    private final MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody, String imdbId) {
        Review review = new Review(reviewBody);
        reviewRepository.save(review);
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();
        return review;
    }

    public List<Review> getReviews(String imdbId) {
        List<Review> reviews = new ArrayList<>();
        reviewRepository.findAll().forEach(review -> reviews.add(review));
        return reviews;
    }
}
