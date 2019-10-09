package com.spring.capstone.backend.admin.controller;

import com.spring.capstone.backend.admin.service.AdminAccountService;
import com.spring.capstone.backend.domain.accounts.Account;
import com.spring.capstone.backend.domain.accounts.AccountRoles;
import com.spring.capstone.backend.domain.accounts.CurrentAccount;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/account")
public class AdminAccountController {

    private AdminAccountService adminAccountService;

    public AdminAccountController(AdminAccountService adminAccountService) {
        this.adminAccountService = adminAccountService;
    }

    @GetMapping
    public ResponseEntity getAccountList(@CurrentAccount Account account, Pageable pageable) {
        if (isNotUnauthorized(account)) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(adminAccountService.getAccountList(pageable), HttpStatus.OK);
    }

    @GetMapping("/size")
    public ResponseEntity<Object> getAccountSize(@CurrentAccount Account account) {
        if (isNotUnauthorized(account)) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        try {
            return new ResponseEntity<>(adminAccountService.getAccountSize(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getAccount(@CurrentAccount Account account, @PathVariable Long id) {
        if (isNotUnauthorized(account)) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        try {
            return new ResponseEntity<>(adminAccountService.getAccount(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private boolean isNotUnauthorized(@CurrentAccount Account account) {
        if (account==null || !account.getRoles().contains(AccountRoles.ADMIN)) {
            return true;
        }
        return false;
    }
}
