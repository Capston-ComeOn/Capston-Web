package com.spring.capstone.backend.controller;

import com.spring.capstone.backend.domain.accounts.Account;
import com.spring.capstone.backend.domain.accounts.CurrentAccount;
import com.spring.capstone.backend.domain.article.Article;
import com.spring.capstone.backend.domain.article.ArticleVO;
import com.spring.capstone.backend.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseEntity<Article> createArticle(@CurrentAccount Account account, @RequestBody @Valid ArticleVO articleVO, Errors errors) {
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (errors.hasErrors()) {
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }
        try {
            long save = articleService.save(account.getEmail(), articleVO);
            return new ResponseEntity(save, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}