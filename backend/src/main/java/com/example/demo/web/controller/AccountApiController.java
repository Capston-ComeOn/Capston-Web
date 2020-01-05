package com.example.demo.web.controller;

import com.example.demo.domain.accounts.Account;
import com.example.demo.domain.accounts.CurrentAccount;
import com.example.demo.service.AccountService;
import com.example.demo.service.dto.AccountRequestDto;
import com.example.demo.service.dto.AccountResponseDto;
import com.example.demo.web.utils.S3Uploader;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/accounts")
public class AccountApiController {

    private AccountService accountService;
    private S3Uploader s3Uploader;

    public AccountApiController(AccountService accountService, S3Uploader s3Uploader) {
        this.accountService = accountService;
        this.s3Uploader = s3Uploader;
    }

    @PostMapping("/join")
    public ResponseEntity createAccount(@RequestBody AccountRequestDto accountRequestDto) {
        return new ResponseEntity(accountService.save(accountRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity getLoginAccount(@CurrentAccount Account account) {

        if (isLogin(account)) return new ResponseEntity(HttpStatus.UNAUTHORIZED);

        AccountResponseDto loginAccount;
        try {
            loginAccount = accountService.getAccountWithEmail(account.getEmail());
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(loginAccount, HttpStatus.OK);
    }

    @PostMapping("/search/name")
    public ResponseEntity searchWithName(@CurrentAccount Account account, @RequestBody AccountResponseDto accountResponseDto) {
        if (isLogin(account)) return new ResponseEntity(HttpStatus.UNAUTHORIZED);

        List<AccountResponseDto> accounts;
        try {
            accounts = accountService.searchWithName(accountResponseDto.getName());
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(accounts, HttpStatus.OK);
    }

    @PostMapping("/files")
    @Description("계정 이미지파일 추가")
    public ResponseEntity addFiles(@CurrentAccount Account account, @RequestBody MultipartFile uploadFile) {
        if (Objects.isNull(account)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        try {
            String fileName = s3Uploader.upload(uploadFile, "static");
            accountService.changeImgSrc(account.getEmail(), fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }


    private boolean isLogin(@CurrentAccount Account account) {
        if (account == null) {
            return true;
        }
        return false;
    }
}
