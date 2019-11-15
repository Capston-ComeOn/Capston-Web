package com.example.demo.service.dto;

import com.example.demo.domain.accounts.Account;
import com.example.demo.domain.article.Article;
import com.example.demo.domain.category.Category;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ArticleResponseDto {

    private Long id;

    private String title;

    private String contents;

    private AccountResponseDto author;

    private CategoryResponse category;

    private long viewer;

    private long recommend;

    private LocalDateTime created;


    protected ArticleResponseDto() {
    }

    public static ArticleResponseDto withArticle(Article article) {
        return new ArticleResponseDto(
                article.getId(),
                article.getTitle(),
                article.getContents(),
                article.getAuthor(),
                article.getCategory(),
                article.getCreated());
    }

    private ArticleResponseDto(Long id, String title, String contents, Account account, Category category, LocalDateTime created) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.author = AccountResponseDto.withAccount(account);
        this.category = CategoryResponse.withCategory(category);
        this.created = created;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public AccountResponseDto getAuthor() {
        return author;
    }

    public CategoryResponse getCategory() {
        return category;
    }

    public String getCreated() {
        return created.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public Long getId() {
        return id;
    }

    public long getViewer() {
        return viewer;
    }

    public long getRecommend() {
        return recommend;
    }
}
