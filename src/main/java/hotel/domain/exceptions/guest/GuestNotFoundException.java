package hotel.domain.exceptions.guest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

public class GuestNotFoundException extends Exception {

    @RestControllerAdvice
    public static class Handler {

        @ResponseStatus(HttpStatus.NOT_FOUND)
        @ExceptionHandler(GuestNotFoundException.class)
        public String handle(GuestNotFoundException e) {
            return e.getMessage();
        }
    }

    public GuestNotFoundException() {
        super("Hospede não encontrado!");
    }
}
