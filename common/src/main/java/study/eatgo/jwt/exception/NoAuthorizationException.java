package study.eatgo.jwt.exception;

public class NoAuthorizationException extends RuntimeException{

    public NoAuthorizationException() {
        super("권한이 없습니다");
    }
}
