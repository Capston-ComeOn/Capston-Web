package com.spring.capstone.backend.etc;

public class ErrorMessage {
    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
