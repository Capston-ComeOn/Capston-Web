package com.spring.capstone.backend.service;

import com.spring.capstone.backend.domain.accounts.Account;
import com.spring.capstone.backend.domain.accounts.AccountRepository;
import com.spring.capstone.backend.domain.article.Article;
import com.spring.capstone.backend.domain.article.ArticleRepository;
import com.spring.capstone.backend.domain.article.ArticleVO;
import com.spring.capstone.backend.domain.category.Category;
import com.spring.capstone.backend.domain.category.CategoryService;
import com.spring.capstone.backend.service.exception.NotFoundDataException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
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
    public long save(String email, ArticleVO articleVO) {
        Account account = findByEmail(email);

        long categoryId = articleVO.getCategoryId();
        Category category = categoryService.getCategory(categoryId);

        Article article = Article.of(account, articleVO, category);
        return articleRepository.save(article).getId();
    }

    public Article getArticle(Long categoryId, Long id) {
        return articleRepository.findByCategoryIdAndId(categoryId, id).orElseThrow(NotFoundDataException::new);
    }

    public Page<Article> getArticles(Long categoryId, Pageable pageable) {
        return articleRepository.findByCategoryId(categoryId, pageable);
    }

    public long getArticleSize(Long categoryId) {
        return articleRepository.countByCategoryId(categoryId);
    }

    @Transactional
    public long update(Long articleId, ArticleVO articleVO) {
        Article article = articleRepository.findById(articleId).orElseThrow(NotFoundDataException::new);
        article.update(articleVO);
        articleRepository.save(article);
        return articleId;
    }

    @Transactional
    public void delete(long articleId) {
        articleRepository.deleteById(articleId);
    }

    private Account findByEmail(String email) {
        return accountRepository.findByEmail(email).orElseThrow(NotFoundDataException::new);
    }
}