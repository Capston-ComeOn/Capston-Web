package com.spring.capstone.backend.service.assembler;

import com.spring.capstone.backend.domain.Article;
import com.spring.capstone.backend.service.dto.ArticleDto;

public class ArticleAssembler {

    public static ArticleDto toDto(Article article) {
        return new ArticleDto(article.getId(), article.getTitle(), article.getContents(),
                article.getAuthor().getId(), article.getAuthor().getName());
    }
}
