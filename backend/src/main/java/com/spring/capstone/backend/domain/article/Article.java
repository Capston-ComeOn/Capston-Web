package com.spring.capstone.backend.domain.article;

import com.spring.capstone.backend.domain.accounts.Account;
import com.spring.capstone.backend.service.exception.InvalidAccountException;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private ArticleContents articleContents;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "author", foreignKey = @ForeignKey(name = "fk_article_to_author"))
    private Account author;

    private Article() {

    }

    public Article(ArticleContents articleContents, Account author) {
        this.articleContents = articleContents;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public ArticleContents getArticleContents() {
        return articleContents;
    }

    public Account getAuthor() {
        return author;
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

    public void checkAuthor(Account account) {
        if(!author.equals(account)) {
            throw new InvalidAccountException("글쓴이만 수정/삭제가 가능합니다.");
        }
    }

    public void update(ArticleContents articleContents, Account account) {
        checkAuthor(account);
        this.articleContents = articleContents;
    }
}
