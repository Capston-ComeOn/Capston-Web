package com.spring.capstone.backend.admin.service;

import com.spring.capstone.backend.domain.accounts.AccountRepository;
import com.spring.capstone.backend.domain.article.Article;
import com.spring.capstone.backend.domain.article.ArticleRepository;
import com.spring.capstone.backend.service.exception.NotFoundDataException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminArticleService {

    private ArticleRepository articleRepository;

    public AdminArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
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
    public void delete(long articleId) {
        articleRepository.deleteById(articleId);
    }

}
