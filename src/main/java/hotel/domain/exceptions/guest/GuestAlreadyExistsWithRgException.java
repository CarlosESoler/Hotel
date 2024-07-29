package hotel.domain.exceptions.guest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

public class GuestAlreadyExistsWithRgException extends Exception {

    @RestControllerAdvice
    public static class Handler {

        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(GuestAlreadyExistsWithRgException.class)
        public String handleUserAlreadyExists(GuestAlreadyExistsWithRgException e) {
            return e.getMessage();
        }
    }

    public GuestAlreadyExistsWithRgException(String rg) {
        super("Hospede já cadastrado! RG do hóspede: " + rg.substring(0, 3) + "." + rg.substring(3, 6) + "." + rg.substring(6, 8) + "-" + rg.substring(8));
    }
}
