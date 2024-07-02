package hotel.domain.exceptions.room;

import br.com.hotel.domain.exceptions.guest.GuestNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

public class RoomNotFoundException extends Exception {
    @RestControllerAdvice
    public static class Handler {
        @ResponseStatus(HttpStatus.NOT_FOUND)
        @ExceptionHandler(RoomNotFoundException.class)
        public String handle(GuestNotFoundException e) {
            return e.getMessage();
        }
    }

    public RoomNotFoundException(String e) {
        super(e);
    }
}
