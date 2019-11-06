package com.spring.capstone.backend.service.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class AccountRequestDto {

    @NotEmpty
    private String name;
    @NotBlank
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String studentId;

    public AccountRequestDto() {
    }

    public AccountRequestDto(String name, String studentId, String email, String password) {
        this.name = name;
        this.studentId = studentId;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }
}
