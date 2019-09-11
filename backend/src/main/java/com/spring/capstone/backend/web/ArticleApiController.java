package com.spring.capstone.backend.web;

import com.spring.capstone.backend.domain.article.ArticleContents;
import com.spring.capstone.backend.service.ArticleService;
import com.spring.capstone.backend.service.dto.LoggedInAccount;
import com.spring.capstone.backend.web.argumentresolver.AccountSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles")
public class ArticleApiController {
    private ArticleService articleService;

    public ArticleApiController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{articleId}")
    public ResponseEntity showArticle(@PathVariable long articleId) {
        return ResponseEntity.ok(articleService.findArticleDto(articleId));
    }

    @GetMapping("/new")
    public ResponseEntity showArticleCreation() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/new")
    public ResponseEntity createArticle(@RequestBody ArticleContents articleContents, @AccountSession LoggedInAccount loggedInAccount) {
        return ResponseEntity.ok(articleService.save(articleContents, loggedInAccount));
    }

    @PutMapping("/{articleId}")
    public ResponseEntity updateArticle(@PathVariable long articleId, @RequestBody ArticleContents articleContents, @AccountSession LoggedInAccount loggedInAccount) {
        return ResponseEntity.ok(articleService.update(articleId, articleContents, loggedInAccount));
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity deleteArticle(@PathVariable long articleId, @AccountSession LoggedInAccount loggedInAccount) {
        articleService.delete(articleId, loggedInAccount);
        return ResponseEntity.ok().build();
    }
}
