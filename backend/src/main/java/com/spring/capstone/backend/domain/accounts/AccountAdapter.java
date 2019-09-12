package com.spring.capstone.backend.domain.accounts;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.*;

public class AccountAdapter extends User {

    private Account account;

    public AccountAdapter(Account account) {
        super(account.getEmail(), account.getPassword(), authorities(account.getRoles()));
        this.account=account;
    }

    private static Collection<? extends GrantedAuthority> authorities(Set<AccountRoles> roles) {
        Iterator<AccountRoles> it = roles.iterator();
        List<SimpleGrantedAuthority> list = new ArrayList<>();

        while (it.hasNext()) {
            list.add(new SimpleGrantedAuthority("ROLE_" + it.next()));
        }
        return list;
    }

    public Account getAccount() {
        return account;
    }
}

