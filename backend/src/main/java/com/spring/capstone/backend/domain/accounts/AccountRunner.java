package com.spring.capstone.backend.domain.accounts;

import com.spring.capstone.backend.domain.article.ArticleRepository;
import com.spring.capstone.backend.service.AccountService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class AccountRunner implements ApplicationRunner {
    private AccountService accountService;
    private ArticleRepository articleRepository;

    public AccountRunner(AccountService accountService, ArticleRepository articleRepository) {
        this.accountService = accountService;
        this.articleRepository = articleRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Account account = new Account("donggyu", "user@gmial.com", "123456789", new HashSet<>(Arrays.asList(AccountRoles.ADMIN, AccountRoles.USER)));

        Account savedAccount = accountService.accountSave(account);
//        Article article = new Article(savedAccount,new ArticleVO("title","cone"));
//        Article article2 = new Article(new ArticleContents("title2", "contents2"), savedAccount);
//
//        articleRepository.save(article);
//        articleRepository.save(article2);
    }
}
