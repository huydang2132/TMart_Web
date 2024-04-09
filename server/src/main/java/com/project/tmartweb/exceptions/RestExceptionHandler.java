package com.project.tmartweb.exceptions;

import com.project.tmartweb.responses.RestData;
import com.project.tmartweb.responses.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<RestData<?>> handleDataNotFoundException(DataNotFoundException e) {
        return RestResponse.error(e.getStatus(), e.getUserMessage(), e.getDevMessage());
    }

    @ExceptionHandler(DataConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<RestData<?>> handleDataConflictException(DataConflictException e) {
        return RestResponse.error(e.getStatus(), e.getUserMessage(), e.getDevMessage());
    }

    @ExceptionHandler(InvalidParamException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<RestData<?>> handleInvalidParamException(InvalidParamException e) {
        return RestResponse.error(e.getStatus(), e.getUserMessage(), e.getDevMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<RestData<?>> handleRuntimeException(RuntimeException e) {
        return RestResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Đã có lỗi xảy ra!", e.getMessage());
    }
}
