package com.spring.capstone.backend.service.exception;

public class NotFoundDataException extends RuntimeException{
    public NotFoundDataException() {
        super("해당 데이터를 찾을 수 없습니다.");
    }
}
