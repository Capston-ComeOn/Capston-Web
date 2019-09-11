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

    public ArticleDto findArticle(long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(NotFoundDataException::new);
        return ArticleAssembler.toDto(article);
    }

    @Transactional
    public void save(ArticleContents articleContents, LoggedInAccount loggedInAccount) {
        Account account = accountRepository.findById(loggedInAccount.getId()).orElseThrow(NotFoundDataException::new);
        Article article = new Article(articleContents, account);
        articleRepository.save(article);
    }
}
