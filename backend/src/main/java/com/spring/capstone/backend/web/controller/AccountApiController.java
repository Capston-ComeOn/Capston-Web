package com.spring.capstone.backend.web.controller;

import com.spring.capstone.backend.domain.accounts.Account;
import com.spring.capstone.backend.domain.accounts.CurrentAccount;
import com.spring.capstone.backend.service.AccountService;
import com.spring.capstone.backend.domain.accounts.AccountVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/loginId")
    public ResponseEntity getLoginAccountId(@CurrentAccount Account account) {
        if (account == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        Account loginAccount;

        try {
            loginAccount = accountService.getAccountWithEmail(account.getEmail());
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(loginAccount.getId(), HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity getLoginAccountWithUserEmail(@CurrentAccount Account account, @PathVariable String email) {
        if (account == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        Account loginAccount;

        try {
            loginAccount = accountService.getAccountWithEmail(email);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(loginAccount, HttpStatus.OK);
    }

    @PostMapping("/search/name")
    public ResponseEntity searchWithName(@CurrentAccount Account account, @RequestBody AccountVO accountVO) {
        if (account == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        System.out.println(accountVO.getName());

        List<Account> accounts;
        try {
            accounts = accountService.searchWithName(accountVO.getName());
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(accounts, HttpStatus.OK);
    }
}
