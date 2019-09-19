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
    private String title;
    @Embedded
    private String contents;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "author", foreignKey = @ForeignKey(name = "fk_article_to_author"))
    private Account author;

    private Article() {

    }

    public String getTitle() {
        return title;
    }

    private Article(Account author, ArticleVO articleVO) {
        this.title = articleVO.getTitle();
        this.contents = articleVO.getContents();
        this.author = author;
    }

    public static Article of(Account author, ArticleVO articleVO) {
        return new Article(author, articleVO);
    }

    public long getId() {
        return id;
    }


    public Account getAuthor() {
        return author;
    }

    public String getContents() {
        return contents;
    }

    public void checkAuthor(Account account) {
        if (!author.equals(account)) {
            throw new InvalidAccountException("글쓴이만 수정/삭제가 가능합니다.");
        }
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
