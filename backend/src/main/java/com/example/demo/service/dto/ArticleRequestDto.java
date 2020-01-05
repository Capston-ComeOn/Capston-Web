package com.example.demo.service.dto;

import javax.validation.constraints.NotEmpty;

public class ArticleRequestDto {

    @NotEmpty
    private String title;

    @NotEmpty
    private String contents;

    protected ArticleRequestDto() {
    }

    public ArticleRequestDto(@NotEmpty String title, @NotEmpty String contents) {
        this.title = title;
        this.contents = contents;
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
}
