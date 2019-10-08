package com.spring.capstone.backend.web.controller;

import com.spring.capstone.backend.service.AccountService;
import com.spring.capstone.backend.domain.accounts.AccountVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountApiController {

    private AccountService accountService;

    public AccountApiController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/join")
    public ResponseEntity createAccount(@RequestBody AccountVO accountDto) {
        accountService.save(accountDto);

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
