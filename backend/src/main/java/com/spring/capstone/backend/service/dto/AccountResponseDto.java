package com.spring.capstone.backend.service.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class AccountResponseDto {

    @NotEmpty
    private String name;
    @NotBlank
    private String email;
    @NotEmpty
    private String studentId;

    public AccountResponseDto() {
    }

    public AccountResponseDto(String name, String studentId, String email) {
        this.name = name;
        this.studentId = studentId;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }
}
