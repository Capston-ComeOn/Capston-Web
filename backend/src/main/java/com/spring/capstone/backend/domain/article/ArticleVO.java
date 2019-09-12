package com.spring.capstone.backend.domain.article;

import javax.validation.constraints.NotEmpty;

public class ArticleVO {

    @NotEmpty
    private String title;
    @NotEmpty
    private String contents;

    public ArticleVO(String title, String contents) {
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
