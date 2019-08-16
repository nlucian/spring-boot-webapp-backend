package com.practice.rest.webservices.exceptions;

import java.time.LocalDate;

public class ExceptionResponse {

    public ExceptionResponse(LocalDate time, String message, String details) {
        this.time = time;
        this.message = message;
        this.details = details;
    }

    private LocalDate time;

    private String message;

    private String details;

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
