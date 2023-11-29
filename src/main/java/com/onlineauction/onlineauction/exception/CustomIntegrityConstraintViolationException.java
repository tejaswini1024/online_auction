package com.onlineauction.onlineauction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomIntegrityConstraintViolationException extends RuntimeException {

    public CustomIntegrityConstraintViolationException() {
        super("Integrity constraint violation occurred");
    }

    public CustomIntegrityConstraintViolationException(String message) {
        super(message);
    }

    public CustomIntegrityConstraintViolationException(String message, SQLIntegrityConstraintViolationException cause) {
        super(message, cause);
    }
}
