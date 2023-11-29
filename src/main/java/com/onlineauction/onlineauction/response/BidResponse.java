package com.onlineauction.onlineauction.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidResponse {

    public static ResponseEntity<Object> createdBidBuilder(HttpStatus httpStatus, Object responseObject) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Successfully created the bid");
        response.put("data result", responseObject);
        response.put("HttpStatus Code", httpStatus);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> getBidBuilder(HttpStatus httpStatus, Object object) {
        Map<String, Object> response = new HashMap<>();
        response.put("HttpStatus Code", httpStatus);
        response.put("data result", object);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> getAllBidsBuilder(HttpStatus httpStatus, List<?> object) {
        Map<String, Object> response = new HashMap<>();
        response.put("HttpStatus Code", httpStatus);
        response.put("data", object);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> updateBidBuilder(HttpStatus httpStatus) {
        Map<String, Object> response = new HashMap<>();
        response.put("HttpStatus code", httpStatus);
        response.put("message", "Accepted bid modification");
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> deleteBidBuilder(HttpStatus httpStatus) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Bid not found with given id");
        response.put("HttpStatus Code", httpStatus);
        return new ResponseEntity<>(response, httpStatus);
    }
}
