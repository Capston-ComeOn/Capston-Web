package com.spring.capstone.backend.service.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ArticleDto {

    @NotEmpty
    private String title;

    @NotEmpty
    private String contents;

    @NotNull
    private Long categoryId;

    public ArticleDto() {
    }

    public ArticleDto(@NotEmpty String title, @NotEmpty String contents, @NotNull Long categoryId) {
        this.title = title;
        this.contents = contents;
        this.categoryId = categoryId;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
