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
        return ResponseEntity.ok(articleService.findArticle(articleId));
    }

    @GetMapping("/new")
    public ResponseEntity showArticleCreation() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/new")
    public ResponseEntity createArticle(@RequestBody ArticleContents articleContents, @AccountSession LoggedInAccount loggedInAccount) {
        articleService.save(articleContents, loggedInAccount);
        return ResponseEntity.ok().build();
    }
}
