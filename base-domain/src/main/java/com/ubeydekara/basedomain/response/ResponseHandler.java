package com.ubeydekara.basedomain.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> generateResponse(HttpStatus status, Object responseObj, String message) {
        Map<String, Object> map = prepareMap(status, responseObj, message);
        return new ResponseEntity<>(map, status);
    }

    public static ResponseEntity<Object> generateResponse(HttpStatus status, Object responseObj) {
        Map<String, Object> map = prepareMap(status, responseObj, null);
        return new ResponseEntity<>(map, status);
    }

    public static ResponseEntity<Object> generateResponse(HttpStatus status, String message) {
        Map<String, Object> map = prepareMap(status, null, message);
        return new ResponseEntity<>(map, status);
    }

    private static Map<String, Object> prepareMap(HttpStatus status, Object responseObj, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", LocalDateTime.now());
        map.put("status", status.value());

        if (responseObj != null)
            map.put("data", responseObj);

        if (message != null)
            map.put("message", message);
        return map;
    }
}