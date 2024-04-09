package com.project.tmartweb.responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RestResponse {
    public static ResponseEntity<RestData<?>> ok(Object data) {
        return ok(HttpStatus.OK, data);
    }

    public static ResponseEntity<RestData<?>> ok(HttpStatus status, Object data) {
        return ok(status, data);
    }

    public static ResponseEntity<RestData<?>> error(HttpStatus status, String userMessage, String devMessage) {
        RestData<?> restData = RestData.error(status, userMessage, devMessage);
        return new ResponseEntity<>(restData, status);
    }

    public static ResponseEntity<RestData<?>> error(HttpStatus status, String devMessage) {
        RestData<?> restData = RestData.error(status, devMessage);
        return new ResponseEntity<>(restData, status);
    }
}
