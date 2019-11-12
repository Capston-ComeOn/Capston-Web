package com.example.demo.service.exception;

public class AlreadyExistsDataException extends RuntimeException {
    public AlreadyExistsDataException(String message) {
        super(message);
    }
}
