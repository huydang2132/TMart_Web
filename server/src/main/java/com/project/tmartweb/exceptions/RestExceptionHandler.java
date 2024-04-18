package com.project.tmartweb.exceptions;

import com.project.tmartweb.responses.RestData;
import com.project.tmartweb.responses.RestResponse;
import jakarta.validation.ValidationException;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class RestExceptionHandler {
    private static final Log logger = LogFactory.getLog(RestExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<RestData<?>> handleNotFoundException(NotFoundException e) {
        logger.error(e.getMessage());
        return RestResponse.error(e.getStatus(), e.getUserMessage(), e.getDevMessage());
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<RestData<?>> handleConflictException(ConflictException e) {
        logger.error(e.getMessage());
        return RestResponse.error(e.getStatus(), e.getUserMessage(), e.getDevMessage());
    }

    @ExceptionHandler(InvalidParamException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestData<?>> handleInvalidParamException(InvalidParamException e) {
        logger.error(e.getMessage());
        return RestResponse.error(e.getStatus(), e.getUserMessage(), e.getDevMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestData<?>> handleValidInputException(ValidationException e) {
        logger.error(e.getMessage());
        return RestResponse.error(HttpStatus.BAD_REQUEST, e.getMessage(), e.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<RestData<?>> handleUnauthorizedException(UnauthorizedException e) {
        logger.error(e.getMessage());
        return RestResponse.error(e.getStatus(), e.getUserMessage(), e.getDevMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<RestData<?>> handleRuntimeException(RuntimeException e) {
        logger.error(e.getMessage());
        return RestResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Đã có lỗi xảy ra!", e.getMessage());
    }
}
