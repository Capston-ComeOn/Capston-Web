package com.spring.capstone.backend.domain.accounts;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "ACCOUNT_ID")
    private Long id;
    @Length(min = 2)
    private String name;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private Set<AccountRoles> roles;

    private Account() {
    }

    public Account(String name, String email, String password, Set<AccountRoles> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
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

    public void setRoles(Set<AccountRoles> roles) {
        this.roles = roles;
    }
}
