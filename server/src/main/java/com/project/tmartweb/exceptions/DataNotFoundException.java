package com.project.tmartweb.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class DataNotFoundException extends RuntimeException {
    private HttpStatus status;

    private String userMessage;

    private String devMessage;

    public DataNotFoundException(String devMessage) {
        super(devMessage);
        this.status = HttpStatus.NOT_FOUND;
        this.devMessage = devMessage;
    }

    public DataNotFoundException(String userMessage, String devMessage) {
        super(devMessage);
        this.status = HttpStatus.NOT_FOUND;
        this.userMessage = userMessage;
        this.devMessage = devMessage;
    }
}
