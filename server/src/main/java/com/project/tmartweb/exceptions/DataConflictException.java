package com.project.tmartweb.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class DataConflictException extends RuntimeException {
    private HttpStatus status;

    private String userMessage;

    private String devMessage;

    public DataConflictException(String devMessage) {
        super(devMessage);
        this.status = HttpStatus.CONFLICT;
        this.devMessage = devMessage;
    }

    public DataConflictException(String userMessage, String devMessage) {
        super(devMessage);
        this.status = HttpStatus.CONFLICT;
        this.userMessage = userMessage;
        this.devMessage = devMessage;
    }
}
