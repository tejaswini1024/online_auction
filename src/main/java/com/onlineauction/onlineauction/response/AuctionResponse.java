package com.onlineauction.onlineauction.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuctionResponse {

    public static ResponseEntity<Object> createdAuctionBuilder(HttpStatus httpStatus, Object responseObject) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Successfully created the auction");
        response.put("data result", responseObject);
        response.put("HttpStatus Code", httpStatus);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> getAuctionBuilder(HttpStatus httpStatus, Object object) {
        Map<String, Object> response = new HashMap<>();
        response.put("HttpStatus Code", httpStatus);
        response.put("data result", object);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> getAllAuctionsBuilder(HttpStatus httpStatus, List<?> object) {
        Map<String, Object> response = new HashMap<>();
        response.put("HttpStatus Code", httpStatus);
        response.put("data", object);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> updateAuctionBuilder(HttpStatus httpStatus) {
        Map<String, Object> response = new HashMap<>();
        response.put("HttpStatus code", httpStatus);
        response.put("message", "Accepted auction modification");
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> deleteAuctionBuilder(HttpStatus httpStatus) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "The auction has been successfully deleted");
        response.put("HttpStatus Code", httpStatus);
        return new ResponseEntity<>(response, httpStatus);
    }
}
