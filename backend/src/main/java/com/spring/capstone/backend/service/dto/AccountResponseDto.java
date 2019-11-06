package com.spring.capstone.backend.service.dto;

import com.spring.capstone.backend.domain.accounts.Account;
import org.hibernate.validator.constraints.Length;

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStudentId(String studentId) {
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
