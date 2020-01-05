package com.example.demo.service.dto;

import com.example.demo.domain.accounts.Account;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class AccountResponseDto {

    private Long id;
    private String name;
    private String email;
    private String studentId;
    private String imgSrc;

    protected AccountResponseDto() {
    }

    public static AccountResponseDto withAccount(Account account) {
        return new AccountResponseDto(
                account.getId(),
                account.getName(),
                account.getEmail(),
                account.getStudentId(),
                account.getImgSrc()
        );
    }

    public AccountResponseDto(Long id, String name, String email, String studentId, String imgSrc) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.studentId = studentId;
        this.imgSrc = imgSrc;
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

    public String getImgSrc() {
        return imgSrc;
    }
}
