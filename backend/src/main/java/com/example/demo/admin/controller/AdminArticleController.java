package com.example.demo.admin.controller;

import com.example.demo.admin.service.AdminArticleService;
import com.example.demo.admin.service.AdminCategoryService;
import com.example.demo.domain.accounts.Account;
import com.example.demo.domain.accounts.AccountRoles;
import com.example.demo.domain.accounts.CurrentAccount;
import com.example.demo.domain.article.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/article")
public class AdminArticleController {

    private AdminArticleService adminArticleService;
    private AdminCategoryService adminCategoryService;

    public AdminArticleController(AdminArticleService adminArticleService, AdminCategoryService adminCategoryService) {
        this.adminArticleService = adminArticleService;
        this.adminCategoryService = adminCategoryService;
    }

    @GetMapping("/{categoryId}/{id}")
    public ResponseEntity<Object> getArticle(@CurrentAccount Account account, @PathVariable Long categoryId, @PathVariable Long id) {
        if (isNotUnauthorized(account)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            return new ResponseEntity<>(adminArticleService.getArticle(categoryId, id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Object> getArticles(@CurrentAccount Account account, @PathVariable Long categoryId, Pageable pageable) {

        if (isNotUnauthorized(account)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            List<Article> articles = adminArticleService.getArticles(categoryId, pageable);
            return new ResponseEntity<>(articles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{categoryId}/size")
    public ResponseEntity<Object> getArticles(@CurrentAccount Account account, @PathVariable Long categoryId) {
        if (isNotUnauthorized(account)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            return new ResponseEntity<>(adminArticleService.getArticleSize(categoryId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/category")
    public ResponseEntity getCategoryList(@CurrentAccount Account account) {
        if (isNotUnauthorized(account)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            return new ResponseEntity<>(adminCategoryService.getCategoryList(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteArticle(@CurrentAccount Account account, @PathVariable Long id) {
        if (isNotUnauthorized(account)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            adminArticleService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private boolean isNotUnauthorized(@CurrentAccount Account account) {
        if (account == null || !account.getRoles().contains(AccountRoles.ADMIN)) {
            return true;
        }
        return false;
    }

}
