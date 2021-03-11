package study.eatgo.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import study.eatgo.error.exceptions.FoodCategoryInvalidException;
import study.eatgo.error.exceptions.MenuNotFoundException;
import study.eatgo.error.exceptions.RegionInvalidException;
import study.eatgo.error.exceptions.RestaurantNotFoundException;
import study.eatgo.error.exceptions.ReviewNotFoundException;
import study.eatgo.error.exceptions.RoleInvalidException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body("요청 변수가 잘못되었습니다");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RestaurantNotFoundException.class)
    public String handleRestaurantNotFoundException(RestaurantNotFoundException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MenuNotFoundException.class)
    public String handleMenuItemNotFoundException(MenuNotFoundException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ReviewNotFoundException.class)
    public String handleReviewNotFoundException(ReviewNotFoundException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RegionInvalidException.class)
    public String HandleRegionInvalidException(RegionInvalidException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FoodCategoryInvalidException.class)
    public String HandleFoodCategoryInvalidException(FoodCategoryInvalidException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RoleInvalidException.class)
    public String HandleRoleInvalidException(RoleInvalidException e) {
        return e.getMessage();
    }
}