package com.spring.capstone.backend.service.assembler;

import com.spring.capstone.backend.domain.article.Article;
import com.spring.capstone.backend.service.dto.ArticleDto;

public class ArticleAssembler {

    public static ArticleDto toDto(Article article) {
        return new ArticleDto(article.getId(), article.getArticleContents(),
                article.getAuthor().getId(), article.getAuthor().getName());
    }
}
