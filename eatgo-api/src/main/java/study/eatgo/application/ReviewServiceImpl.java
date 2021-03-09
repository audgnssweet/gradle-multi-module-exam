package study.eatgo.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.eatgo.domain.Restaurant;
import study.eatgo.domain.RestaurantRepository;
import study.eatgo.domain.Review;
import study.eatgo.domain.ReviewRepository;
import study.eatgo.dto.ReviewDto;
import study.eatgo.exception.exceptions.RestaurantNotFoundException;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final RestaurantRepository restaurantRepository;

    @Override
    public Integer addReview(Integer restaurantId, ReviewDto.Request review) {
        final Restaurant foundRestaurant = restaurantRepository.findById(restaurantId)
            .orElseThrow(RestaurantNotFoundException::new);

        Review saving = Review.builder()
            .username(review.getUsername())
            .score(review.getScore())
            .content(review.getContent())
            .restaurant(foundRestaurant)
            .build();

        final Review savedReview = reviewRepository.save(saving);
        return savedReview.getId();
    }

}
