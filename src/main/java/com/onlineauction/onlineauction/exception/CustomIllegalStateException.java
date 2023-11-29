package com.onlineauction.onlineauction.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomIllegalStateException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CustomIllegalStateException() {
        super("Cannot place bid on a closed auction");
    }
    public CustomIllegalStateException(String message ) {
        super(message);
    }

}
