package com.spring.capstone.backend.service.dto;

import com.spring.capstone.backend.domain.article.ArticleContents;

public class ArticleDto {
    private long articleId;
    private ArticleContents articleContents;
    private long authorId;
    private String authorName;

    public ArticleDto(long articleId, ArticleContents articleContents, long authorId, String authorName) {
        this.articleId = articleId;
        this.articleContents = articleContents;
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public long getArticleId() {
        return articleId;
    }

    public ArticleContents getArticleContents() {
        return articleContents;
    }

    public long getAuthorId() {
        return authorId;
    }

    public String getAuthorName() {
        return authorName;
    }
}

