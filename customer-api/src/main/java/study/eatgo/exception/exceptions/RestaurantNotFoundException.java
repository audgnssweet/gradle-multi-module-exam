package study.eatgo.exception.exceptions;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException() {
        super("해당 ID의 레스토랑은 없습니다");
    }

}
