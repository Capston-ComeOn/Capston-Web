package com.spring.capstone.backend.service.dto;

public class AuthenticationAccountDto {
    private String email;
    private String password;

    public AuthenticationAccountDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
