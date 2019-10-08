package com.spring.capstone.backend.domain.article;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ArticleVO {

    @NotEmpty
    private String title;

    @NotEmpty
    private String contents;

    @NotNull
    private Long categoryId;

    public ArticleVO(@NotEmpty String title, @NotEmpty String contents, @NotNull Long categoryId) {
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
