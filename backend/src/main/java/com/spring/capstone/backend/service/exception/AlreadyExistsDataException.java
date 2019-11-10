package com.spring.capstone.backend.service.exception;

public class AlreadyExistsDataException extends RuntimeException {
    public AlreadyExistsDataException(String message) {
        super(message);
    }
}
