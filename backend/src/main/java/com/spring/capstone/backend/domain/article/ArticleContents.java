package com.spring.capstone.backend.domain.article;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import java.util.Objects;

@Embeddable
public class ArticleContents {

    @Column(nullable = false)
    @Length(max = 30)
    private String title;

    @Lob
    @Column(nullable = false)
    private String contents;

    private ArticleContents() {
    }

    public ArticleContents(@Length(max = 30) String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleContents that = (ArticleContents) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(contents, that.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, contents);
    }
}
