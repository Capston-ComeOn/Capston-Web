package com.spring.capstone.backend.web.configs;

import com.spring.capstone.backend.domain.accounts.Account;
import com.spring.capstone.backend.domain.accounts.AccountRoles;
import com.spring.capstone.backend.domain.article.ArticleVO;
import com.spring.capstone.backend.domain.category.Category;
import com.spring.capstone.backend.domain.category.CategoryRepository;
import com.spring.capstone.backend.service.AccountService;
import com.spring.capstone.backend.service.ArticleService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class Runner implements ApplicationRunner {
    private AccountService accountService;
    private ArticleService articleService;
    private CategoryRepository categoryRepository;

    public Runner(AccountService accountService, ArticleService articleService, CategoryRepository categoryRepository) {
        this.accountService = accountService;
        this.articleService = articleService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        String names[] = {"김동규", "김희주", "이상훈", "곽병선", "안태회"};

        Account account = new Account("donggyu", "user@email.com", "123", new HashSet<>(Arrays.asList(AccountRoles.ADMIN, AccountRoles.USER)));
        accountService.saveAccount(account);
        Account account2 = new Account("sanghun", "user1@email.com", "123", new HashSet<>(Arrays.asList(AccountRoles.ADMIN, AccountRoles.USER)));
        accountService.saveAccount(account2);

//        int index = 0;
//        for (int i = 0; i < 10; i++) {
//            for (String name : names) {
//                Account account1 = new Account(name, "user" + index + "@email.com", "123", new HashSet<>(Arrays.asList(AccountRoles.ADMIN, AccountRoles.USER)));
//                accountService.saveAccount(account1);
//                index++;
//            }
//        }

        Category category = Category.withName("학과 게시판");
        Category category1 = Category.withName("자유 게시판");
        Category category2 = Category.withName("학부생 게시판");
        Category category3 = Category.withName("학년별 게시판");

        Category save = categoryRepository.save(category);
        Category save1 = categoryRepository.save(category1);
        Category save2 = categoryRepository.save(category2);
        Category save3 = categoryRepository.save(category3);

        for (int i = 0; i < 100; i++) {
            ArticleVO articleVO = new ArticleVO("제목" + i, "내용" + i, save.getId());
            articleService.save(account.getEmail(), articleVO);
        }

        for (int i = 100; i < 200; i++) {
            ArticleVO articleVO = new ArticleVO("제목" + i, "내용" + i, save1.getId());
            articleService.save(account.getEmail(), articleVO);
        }

        for (int i = 200; i < 300; i++) {
            ArticleVO articleVO = new ArticleVO("제목" + i, "내용" + i, save2.getId());
            articleService.save(account.getEmail(), articleVO);
        }

        for (int i = 300; i < 400; i++) {
            ArticleVO articleVO = new ArticleVO("제목" + i, "내용" + i, save3.getId());
            articleService.save(account.getEmail(), articleVO);
        }
    }
}
