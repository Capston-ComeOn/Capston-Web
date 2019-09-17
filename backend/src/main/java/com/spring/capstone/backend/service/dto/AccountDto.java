package com.spring.capstone.backend.service.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class AccountDto {

    @Length(min = 2)
    private String name;
    @NotBlank
    private String email;
    @Length(min = 8, max = 12)
    private String password;

    public AccountDto(String name, String email, String password) {
        this.name = name;
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

}
