package com.spring.capstone.backend.domain.article;

import com.spring.capstone.backend.domain.accounts.Account;
import com.spring.capstone.backend.domain.category.Category;
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


    private Article(Account author, ArticleVO articleVO, Category category) {
        this.title = articleVO.getTitle();
        this.contents = articleVO.getContents();
        this.author = author;
        this.category = category;
    }

    private Article() {

    }

    public static Article of(Account author, ArticleVO articleVO, Category category) {
        return new Article(author, articleVO, category);
    }

    public void update(ArticleVO articleVO) {
        this.title = articleVO.getTitle();
        this.contents = articleVO.getContents();
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
