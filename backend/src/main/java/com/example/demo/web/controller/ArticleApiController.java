package com.example.demo.web.controller;

import com.example.demo.domain.accounts.Account;
import com.example.demo.domain.accounts.CurrentAccount;
import com.example.demo.domain.article.Article;
import com.example.demo.service.ArticleService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.dto.ArticleRequestDto;
import com.example.demo.service.dto.ArticleResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleApiController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{categoryId}/{articleId}")
    public ResponseEntity<ArticleResponseDto> getArticle(@CurrentAccount Account account, @PathVariable Long categoryId, @PathVariable Long articleId) {
        if (isLogin(account)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        try {
            ArticleResponseDto article = articleService.getArticle(categoryId, articleId);
            return new ResponseEntity<>(article, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Object> getArticles(@CurrentAccount Account account, @PathVariable Long categoryId, Pageable pageable) {

        if (isLogin(account)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        try {
            List<Article> articles = articleService.getArticles(categoryId, pageable);
            return new ResponseEntity<>(articles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{categoryId}/size")
    public ResponseEntity<Object> getArticlesSize(@CurrentAccount Account account, @PathVariable Long categoryId) {
        if (isLogin(account)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        try {
            long articleSize = articleService.getArticleSize(categoryId);
            return new ResponseEntity<>(articleSize, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{categoryId}")
    public ResponseEntity createArticle(@CurrentAccount Account account, @PathVariable Long categoryId, @RequestBody @Valid ArticleRequestDto articleRequestDto, Errors errors) {
        if (isLogin(account)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if (isConvertError(errors)) return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

        try {
            articleService.save(account.getEmail(), categoryId, articleRequestDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<Object> updateArticle(@CurrentAccount Account account, @PathVariable Long articleId, @RequestBody @Valid ArticleRequestDto articleRequestDto, Errors errors) {
        if (isLogin(account)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if (isConvertError(errors)) return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        try {
            long save = articleService.update(articleId, articleRequestDto);
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity deleteArticle(@CurrentAccount Account account, @PathVariable Long articleId) {
        if (isLogin(account)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        try {
            articleService.delete(articleId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/category")
    public ResponseEntity getCategories(@CurrentAccount Account account) {
        if (isLogin(account)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        try {
            return new ResponseEntity<>(categoryService.getCategoryList(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private boolean isLogin(@CurrentAccount Account account) {
        if (account == null) {
            return true;
        }
        return false;
    }

    private boolean isConvertError(Errors errors) {
        if (errors.hasErrors()) {
            return true;
        }
        return false;
    }
}