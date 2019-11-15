package com.example.demo.service;

import com.example.demo.domain.accounts.Account;
import com.example.demo.domain.accounts.AccountRepository;
import com.example.demo.domain.article.Article;
import com.example.demo.domain.article.ArticleRepository;
import com.example.demo.domain.category.Category;
import com.example.demo.service.dto.ArticleRequestDto;
import com.example.demo.service.dto.ArticleResponseDto;
import com.example.demo.service.exception.NotFoundDataException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ArticleResponseDto> getArticles(Long categoryId, Pageable pageable) {
        return articleRepository.findByCategoryId(categoryId, pageable)
                .stream()
                .map(a -> ArticleResponseDto.withArticle(a))
                .collect(Collectors.toList());
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