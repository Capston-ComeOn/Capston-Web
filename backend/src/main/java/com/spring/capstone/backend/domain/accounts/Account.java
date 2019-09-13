package com.spring.capstone.backend.domain.accounts;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Account {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private Set<AccountRoles> roles;

    public Account() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setRoles(Set<AccountRoles> roles) {
        this.roles = roles;
    }
}
