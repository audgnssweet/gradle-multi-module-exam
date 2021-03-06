package study.eatgo.domain.restaurant.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import study.eatgo.domain.restaurant.domain.Restaurant;
import study.eatgo.domain.restaurant.model.Region;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findAllByRegion(Region region);
}
