package com.spring.capstone.backend.service;

import com.spring.capstone.backend.domain.accounts.Account;
import com.spring.capstone.backend.domain.accounts.AccountRepository;
import com.spring.capstone.backend.domain.article.Article;
import com.spring.capstone.backend.domain.article.ArticleRepository;
import com.spring.capstone.backend.domain.article.ArticleVO;
import com.spring.capstone.backend.service.exception.NotFoundDataException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;
    private AccountRepository accountRepository;

    public ArticleService(ArticleRepository articleRepository, AccountRepository accountRepository) {
        this.articleRepository = articleRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public long save(String email, ArticleVO articleVO) {
        Account account = findByEmail(email);
        Article article = Article.of(account, articleVO);
        return articleRepository.save(article).getId();
    }

    public Article getArticle(Long id) {
        return articleRepository.findById(id).orElseThrow(NotFoundDataException::new);
    }

    public Page<Article> getArticles(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

//    @Transactional(readOnly = true)
//    public ArticleDto findArticleDto(long articleId) {
//        Article article = findArticle(articleId);
//        return ArticleAssembler.toDto(article);
//    }

//    @Transactional
//    public long update(long articleId, ArticleContents articleContents, LoggedInAccount loggedInAccount) {
//        Article article = findArticle(articleId);
//        Account account = findAccount(loggedInAccount);
//        article.update(articleContents, account);
//        return articleId;
//    }

//    @Transactional
//    public void delete(long articleId, LoggedInAccount loggedInAccount) {
//        Article article = findArticle(articleId);
//        Account account = findAccount(loggedInAccount);
//        article.checkAuthor(account);
//        articleRepository.deleteById(articleId);
//    }

    private Account findByEmail(String email) {
        return accountRepository.findByEmail(email).orElseThrow(NotFoundDataException::new);
    }
}
