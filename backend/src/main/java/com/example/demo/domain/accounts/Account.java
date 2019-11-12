package com.example.demo.domain.accounts;


import com.example.demo.service.dto.AccountRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private Long id;
    @Length(min = 2)
    private String name;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String studentId;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private Set<AccountRoles> roles;

    private String imgSrc;

    protected Account() {

    }

    public static Account of(AccountRequestDto accountRequestDto) {
        return new Account(
                accountRequestDto.getName(),
                accountRequestDto.getEmail(),
                accountRequestDto.getStudentId(),
                accountRequestDto.getPassword()
        );
    }

    private Account(String name, String email, String studentId, String password) {
        this.name = name;
        this.email = email;
        this.studentId = studentId;
        this.password = password;
        this.roles = new HashSet<AccountRoles>(Arrays.asList(AccountRoles.USER));
        this.imgSrc = null;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return id.equals(account.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<AccountRoles> getRoles() {
        return roles;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
}
