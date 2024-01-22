package br.com.hotel.domain.exceptions.guest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

public class GuestAlreadyExistsException extends Exception {

    @RestControllerAdvice
    public static class Handler {

        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(GuestAlreadyExistsException.class)
        public String handleUserAlreadyExists(GuestAlreadyExistsException e) {
            return e.getMessage();
        }
    }

    public GuestAlreadyExistsException(String e, String document) {
        super(e + " CPF / CPNJ do hospede ja cadastrado: " + document);
    }
}
