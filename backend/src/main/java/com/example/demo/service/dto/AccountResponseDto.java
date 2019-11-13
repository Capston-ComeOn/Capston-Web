package com.example.demo.service.dto;

import com.example.demo.domain.accounts.Account;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class AccountResponseDto {

    private Long id;
    @NotEmpty
    private String name;
    @NotBlank
    private String email;
    @NotEmpty
    private String studentId;

    protected AccountResponseDto() {
    }

    public static AccountResponseDto withAccount(Account account) {
        return new AccountResponseDto(account.getId(), account.getName(), account.getEmail(), account.getStudentId());
    }

    public AccountResponseDto(Long id, @NotEmpty String name, @NotBlank String email, @NotEmpty String studentId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.studentId = studentId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getStudentId() {
        return studentId;
    }
}
