package com.js.calendar.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
        CalendarException exception = new CalendarException(e.getSQLException().getLocalizedMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(exception, exception.getStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleRequiredBodyMissing(HttpMessageNotReadableException e ) {
        CalendarException exception = new CalendarException(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(exception, exception.getStatus());
    }

    @ExceptionHandler(NoEntityException.class)
    public ResponseEntity<Object> handleNoSuchEntityException(NoEntityException e) {
        CalendarException exception = new CalendarException(e.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(exception, exception.getStatus());
    }
}
