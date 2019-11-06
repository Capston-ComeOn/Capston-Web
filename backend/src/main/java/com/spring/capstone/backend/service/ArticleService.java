package com.spring.capstone.backend.service;

import com.spring.capstone.backend.domain.accounts.Account;
import com.spring.capstone.backend.domain.accounts.AccountRepository;
import com.spring.capstone.backend.domain.article.Article;
import com.spring.capstone.backend.domain.article.ArticleRepository;
import com.spring.capstone.backend.service.dto.ArticleRequestDto;
import com.spring.capstone.backend.domain.category.Category;
import com.spring.capstone.backend.service.dto.ArticleResponseDto;
import com.spring.capstone.backend.service.exception.NotFoundDataException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ArticleService {

    private ArticleRepository articleRepository;
    private AccountRepository accountRepository;
    private CategoryService categoryService;

    public ArticleService(ArticleRepository articleRepository, AccountRepository accountRepository, CategoryService categoryService) {
        this.articleRepository = articleRepository;
        this.accountRepository = accountRepository;
        this.categoryService = categoryService;
    }

    @Transactional
    public long save(String email, long categoryId, ArticleRequestDto articleRequestDto) {
        Account account = accountRepository.findByEmail(email).orElseThrow(NotFoundDataException::new);
        Category category = categoryService.getCategory(categoryId);
        return articleRepository.save(Article.of(account, articleRequestDto, category));
    }

    public ArticleResponseDto getArticle(Long categoryId, Long articleId) {
        Article article = articleRepository.findByCategoryIdAndArticleId(categoryId, articleId);
        return ArticleResponseDto.withArticle(article);
    }

    public List<Article> getArticles(Long categoryId, Pageable pageable) {
        return articleRepository.findByCategoryId(categoryId,pageable);
    }

    public long getArticleSize(Long categoryId) {
        return articleRepository.countByCategoryId(categoryId);
    }

    @Transactional
    public long update(Long articleId, ArticleRequestDto articleRequestDto) {
        Article article = articleRepository.findById(articleId);
        article.update(articleRequestDto);
        articleRepository.save(article);
        return articleId;
    }

    @Transactional
    public void delete(long articleId) {
//        articleRepository.deleteById(articleId);
    }

}