package com.example.demo.service.dto;

import com.example.demo.domain.accounts.Account;
import com.example.demo.domain.article.Article;
import com.example.demo.domain.category.Category;

public class ArticleResponseDto {

    private String title;

    private String contents;

    private AccountResponseDto author;

    private CategoryResponse category;

    protected ArticleResponseDto() {
    }

    public static ArticleResponseDto withArticle(Article article) {
        return new ArticleResponseDto(article.getTitle(), article.getContents(), article.getAuthor(), article.getCategory());
    }

    private ArticleResponseDto(String title, String contents, Account account, Category category) {
        this.title = title;
        this.contents = contents;
        this.author = AccountResponseDto.withAccount(account);
        this.category = CategoryResponse.withCategory(category);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public AccountResponseDto getAuthor() {
        return author;
    }

    public CategoryResponse getCategory() {
        return category;
    }
}
