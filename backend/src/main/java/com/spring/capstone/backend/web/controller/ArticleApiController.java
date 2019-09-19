package com.spring.capstone.backend.web.controller;

import com.spring.capstone.backend.domain.accounts.Account;
import com.spring.capstone.backend.domain.accounts.CurrentAccount;
import com.spring.capstone.backend.domain.article.ArticleVO;
import com.spring.capstone.backend.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/article")
public class ArticleApiController {

    @Autowired
    private ArticleService articleService;


    @GetMapping("/{id}")
    public ResponseEntity<Object> getArticle(@CurrentAccount Account account, @PathVariable Long id) {

        if (account == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            return new ResponseEntity<>(articleService.getArticle(id), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getArticles(@CurrentAccount Account account, Pageable pageable) {
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            return new ResponseEntity<>(articleService.getArticles(pageable), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createArticle(@CurrentAccount Account account, @RequestBody @Valid ArticleVO articleVO, Errors errors) {
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        try {
            long save = articleService.save(account.getEmail(), articleVO);
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}