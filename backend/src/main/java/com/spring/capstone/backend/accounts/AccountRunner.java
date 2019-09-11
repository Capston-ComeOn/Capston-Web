package com.spring.capstone.backend.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class AccountRunner implements ApplicationRunner {

    @Autowired
    private AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Account account=new Account();
        account.setEmail("user@email.com");
        account.setPassword("123");
        account.setName("donggyu");
        account.setRoles(new HashSet<>(Arrays.asList(AccountRoles.ADMIN,AccountRoles.USER)));

        accountService.accountSave(account);
    }
}
