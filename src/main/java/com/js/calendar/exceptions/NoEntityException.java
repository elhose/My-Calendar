package com.js.calendar.exceptions;

public class NoEntityException extends RuntimeException {
    public NoEntityException(Long id) {
        super("No such entity with ID: " + id + " in Database!");
    }

    public NoEntityException() {
        super("No such entities in Database!");
    }
}
