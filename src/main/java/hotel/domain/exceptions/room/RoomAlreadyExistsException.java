package hotel.domain.exceptions.room;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

public class RoomAlreadyExistsException extends Exception {

    @RestControllerAdvice
    public static class Handler {
        @ResponseStatus(HttpStatus.NOT_FOUND)
        @ExceptionHandler(RoomAlreadyExistsException.class)
        public String handle(RoomAlreadyExistsException e) {
            return e.getMessage();
        }
    }

    public RoomAlreadyExistsException() {
        super("O quarto já existe!");
    }
}
