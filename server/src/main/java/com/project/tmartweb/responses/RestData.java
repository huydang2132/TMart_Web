package com.project.tmartweb.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

public class RestData<Data> {
    private HttpStatus status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String devMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Data data;

    public RestData(HttpStatus status, String userMessage, String devMessage, Data data) {
        this.status = status;
        this.userMessage = userMessage;
        this.devMessage = devMessage;
        this.data = data;
    }

    public RestData(HttpStatus status, String userMessage, String devMessage) {
        this.status = status;
        this.userMessage = userMessage;
        this.devMessage = devMessage;
    }

    public RestData(HttpStatus status, String devMessage, Data data) {
        this.status = status;
        this.devMessage = devMessage;
        this.data = data;
    }

    public RestData(HttpStatus status, String devMessage) {
        this.status = status;
        this.devMessage = devMessage;
    }

    public static RestData<?> error(HttpStatus status, String userMessage, String devMessage) {
        return new RestData<>(status, userMessage, devMessage);
    }

    public static RestData<?> error(HttpStatus status, String devMessage) {
        return new RestData<>(status, devMessage);
    }
}
