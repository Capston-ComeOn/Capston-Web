package com.spring.capstone.backend.domain.article;

import com.spring.capstone.backend.domain.accounts.Account;
import com.spring.capstone.backend.domain.category.Category;
import com.spring.capstone.backend.service.dto.ArticleDto;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ARTICLE_ID")
    private long id;

    private String title;

    @Lob
    private String contents;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account author;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;


    private Article(Account author, ArticleDto articleDto, Category category) {
        this.title = articleDto.getTitle();
        this.contents = articleDto.getContents();
        this.author = author;
        this.category = category;
    }

    public Article() {

    }

    public static Article of(Account author, ArticleDto articleDto, Category category) {
        return new Article(author, articleDto, category);
    }

    public void update(ArticleDto articleDto) {
        this.title = articleDto.getTitle();
        this.contents = articleDto.getContents();
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
