package hotel.exceptions.room;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

public class RoomNotFoundException extends Exception {
    @RestControllerAdvice
    public static class Handler {
        @ResponseStatus(HttpStatus.NOT_FOUND)
        @ExceptionHandler(RoomNotFoundException.class)
        public String handle(RoomNotFoundException e) {
            return e.getMessage();
        }
    }

    public RoomNotFoundException() {
        super("Quarto não encontrado!");
    }
}
