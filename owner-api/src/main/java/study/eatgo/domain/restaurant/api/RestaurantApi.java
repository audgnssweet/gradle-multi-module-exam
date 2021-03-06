package study.eatgo.domain.restaurant.api;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import study.eatgo.annotations.Owner;
import study.eatgo.domain.restaurant.application.RestaurantInfoService;
import study.eatgo.domain.restaurant.application.RestaurantUpdateService;
import study.eatgo.domain.restaurant.domain.Restaurant;
import study.eatgo.domain.restaurant.dto.MenuUpdateRequest;
import study.eatgo.domain.restaurant.dto.MyRestaurantResponse;
import study.eatgo.domain.review.application.ReviewRemoveService;
import study.eatgo.domain.user.domain.User;

@RequestMapping("/restaurants")
@RequiredArgsConstructor
@RestController
public class RestaurantApi {

    private final RestaurantUpdateService restaurantUpdateService;

    private final ReviewRemoveService reviewRemoveService;

    private final RestaurantInfoService restaurantInfoService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/me")
    public MyRestaurantResponse getRestaurantInfo(@Owner User owner) {

        final Restaurant restaurant = restaurantInfoService.getRestaurantInfo(owner.getId());
        return new MyRestaurantResponse(restaurant);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/me/menu")
    public void updateMenu(@RequestBody @Valid MenuUpdateRequest dto,
        @Owner User owner) {

        restaurantUpdateService.updateMenu(owner.getId(), dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/me")
    public void deleteRestaurant(@Owner User owner) {

        reviewRemoveService.removeAllInRestaurant(owner);
        restaurantUpdateService.delete(owner);
    }

}
