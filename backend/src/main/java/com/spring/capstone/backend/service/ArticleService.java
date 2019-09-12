package com.spring.capstone.backend.service;

import com.spring.capstone.backend.domain.accounts.Account;
import com.spring.capstone.backend.domain.accounts.AccountRepository;
import com.spring.capstone.backend.domain.article.Article;
import com.spring.capstone.backend.domain.article.ArticleContents;
import com.spring.capstone.backend.domain.article.ArticleRepository;
import com.spring.capstone.backend.service.assembler.ArticleAssembler;
import com.spring.capstone.backend.service.dto.ArticleDto;
import com.spring.capstone.backend.service.dto.LoggedInAccount;
import com.spring.capstone.backend.service.exception.NotFoundDataException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;
    private AccountRepository accountRepository;

    public ArticleService(ArticleRepository articleRepository, AccountRepository accountRepository) {
        this.articleRepository = articleRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional(readOnly = true)
    public ArticleDto findArticleDto(long articleId) {
        Article article = findArticle(articleId);
        return ArticleAssembler.toDto(article);
    }

    @Transactional
    public long save(ArticleContents articleContents, LoggedInAccount loggedInAccount) {
        Account account = findAccount(loggedInAccount);
        Article article = new Article(articleContents, account);
        return articleRepository.save(article).getId();
    }

    @Transactional
    public long update(long articleId, ArticleContents articleContents, LoggedInAccount loggedInAccount) {
        Article article = findArticle(articleId);
        Account account = findAccount(loggedInAccount);
        article.update(articleContents, account);
        return articleId;
    }

    @Transactional
    public void delete(long articleId, LoggedInAccount loggedInAccount) {
        Article article = findArticle(articleId);
        Account account = findAccount(loggedInAccount);
        article.checkAuthor(account);
        articleRepository.deleteById(articleId);
    }

    private Article findArticle(long articleId) {
        return articleRepository.findById(articleId).orElseThrow(NotFoundDataException::new);
    }

    private Account findAccount(LoggedInAccount loggedInAccount) {
        return accountRepository.findById(loggedInAccount.getId()).orElseThrow(NotFoundDataException::new);
    }
}
