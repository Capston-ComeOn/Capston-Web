package com.spring.capstone.backend.service;

import com.spring.capstone.backend.domain.Article;
import com.spring.capstone.backend.domain.ArticleRepository;
import com.spring.capstone.backend.service.assembler.ArticleAssembler;
import com.spring.capstone.backend.service.dto.ArticleDto;
import com.spring.capstone.backend.service.exception.NotFoundDataException;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleDto findArticle(long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(NotFoundDataException::new);
        return ArticleAssembler.toDto(article);
    }
}
