package com.spring.capstone.backend.accounts;

import com.spring.capstone.backend.domain.Article;
import com.spring.capstone.backend.domain.ArticleRepository;
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

        Account account=new Account();
        account.setEmail("user@email.com");
        account.setPassword("123");
        account.setName("donggyu");
        account.setRoles(new HashSet<>(Arrays.asList(AccountRoles.ADMIN,AccountRoles.USER)));

        Account savedAccount=accountService.accountSave(account);
        Article article=new Article("title","contents",savedAccount);

        articleRepository.save(article);
    }
}
