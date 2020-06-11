package com.js.calendar.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class CalendarException {
    private final String message;
    private final HttpStatus status;
    private final LocalDateTime time;

    public CalendarException(String message, HttpStatus status, LocalDateTime time) {
        this.message = message;
        this.status = status;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
