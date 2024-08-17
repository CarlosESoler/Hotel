package hotel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

public class GuestAddressNotFoundException extends Exception {


    @RestControllerAdvice
    public static class Handler {

        @ResponseStatus(HttpStatus.NOT_FOUND)
        @ExceptionHandler(GuestAddressNotFoundException.class)
        public String handleGuestAddressNotFoundException(GuestAddressNotFoundException e) {
            return e.getMessage();
        }
    }

    public GuestAddressNotFoundException() {
        super("Hospede não possui endereço cadastrado!");
    }
}