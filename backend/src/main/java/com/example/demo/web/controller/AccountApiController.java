package com.example.demo.web.controller;

import com.example.demo.domain.accounts.Account;
import com.example.demo.domain.accounts.CurrentAccount;
import com.example.demo.service.AccountService;
import com.example.demo.service.dto.AccountRequestDto;
import com.example.demo.service.dto.AccountResponseDto;
import org.springframework.context.annotation.Description;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
public class AccountApiController {

    private AccountService accountService;

    public AccountApiController(AccountService accountService) {
        this.accountService = accountService;
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
    public ResponseEntity addFiles(@CurrentAccount Account account, @RequestBody MultipartFile[] uploadFile) {

        String path = "/Users/kimdonggyu/Desktop/Capston-Web/backend/src/main/resources/static/upload";

        AccountResponseDto loginAccount = accountService.getAccountWithEmail(account.getEmail());

        for (MultipartFile multipartFile : uploadFile) {
            String originalFilename = multipartFile.getOriginalFilename();
            String uploadFileName = UUID.randomUUID() + "_" + originalFilename.substring(originalFilename.lastIndexOf("\\") + 1);

            try {
                multipartFile.transferTo(new File(path, uploadFileName));
// TODO                loginAccount.setImgSrc(uploadFileName);
//                accountService.saveAccount(loginAccount);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    // http://localhost:8090/api/accounts/download?fileName=${uuid}_${fileName}`"
    @GetMapping("/download")
    @Description("계정 이미지파일 다운로드")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(String fileName) {
        String path = "/Users/kimdonggyu/Desktop/Capston-Web/backend/src/main/resources/static/upload/";
        Resource resource;
        try {
            resource = new FileSystemResource(path + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(resource, HttpStatus.OK);
    }


    private boolean isLogin(@CurrentAccount Account account) {
        if (account == null) {
            return true;
        }
        return false;
    }
}
