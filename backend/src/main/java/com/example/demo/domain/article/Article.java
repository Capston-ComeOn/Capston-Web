package com.example.demo.domain.article;

import com.example.demo.domain.accounts.Account;
import com.example.demo.domain.category.Category;
import com.example.demo.service.dto.ArticleRequestDto;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ARTICLE_ID")
    private long id;

    private String title;

    @Lob
    private String contents;

    @ManyToOne(fetch = LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account author;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;


    private Article(Account author, ArticleRequestDto articleRequestDto, Category category) {
        this.title = articleRequestDto.getTitle();
        this.contents = articleRequestDto.getContents();
        this.author = author;
        this.category = category;
    }

    protected Article() {

    }

    public static Article of(Account author, ArticleRequestDto articleRequestDto, Category category) {
        return new Article(author, articleRequestDto, category);
    }

    public void update(ArticleRequestDto articleRequestDto) {
        this.title = articleRequestDto.getTitle();
        this.contents = articleRequestDto.getContents();
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Account getAuthor() {
        return author;
    }

    public String getContents() {
        return contents;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id == article.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
