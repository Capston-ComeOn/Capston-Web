package com.spring.capstone.backend.service.dto;

public class ArticleDto {
    private long articleId;
    private String title;
    private String contents;
    private long authorId;
    private String authorName;

    public ArticleDto(long articleId, String title, String contents, long authorId, String authorName) {
        this.articleId = articleId;
        this.title = title;
        this.contents = contents;
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public long getArticleId() {
        return articleId;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public long getAuthorId() {
        return authorId;
    }

    public String getAuthorName() {
        return authorName;
    }
}
