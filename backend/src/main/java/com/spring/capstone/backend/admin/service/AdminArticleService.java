package com.spring.capstone.backend.admin.service;

import com.spring.capstone.backend.domain.article.Article;
import com.spring.capstone.backend.domain.article.ArticleRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminArticleService {

    private ArticleRepository articleRepository;

    public AdminArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article getArticle(Long categoryId, Long id) {
        return articleRepository.findByCategoryIdAndArticleId(categoryId, id);
    }

    public List<Article> getArticles(Long categoryId, Pageable pageable) {
        return articleRepository.findByCategoryId(categoryId,pageable);
    }

    public long getArticleSize(Long categoryId) {
        return articleRepository.countByCategoryId(categoryId);
    }

    @Transactional
    public void delete(long articleId) {
//        articleRepository.deleteById(articleId);
    }

}
