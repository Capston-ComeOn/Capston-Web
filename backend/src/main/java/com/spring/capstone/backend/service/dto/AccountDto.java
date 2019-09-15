package com.spring.capstone.backend.service.dto;

import com.spring.capstone.backend.domain.accounts.AccountRoles;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class AccountDto {

    @Length(min = 2)
    private String name;
    @NotBlank
    private String email;
    @Length(min = 8, max = 12)
    private String password;
    private Set<AccountRoles> roles;

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

    public Set<AccountRoles> getRoles() {
        return roles;
    }

    public void setRoles(Set<AccountRoles> roles) {
        this.roles = roles;
    }
}
