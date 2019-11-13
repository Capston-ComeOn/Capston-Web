package com.example.demo.web.configs;

import com.example.demo.domain.category.Category;
import com.example.demo.domain.category.CategoryRepository;
import com.example.demo.service.AccountService;
import com.example.demo.service.ArticleService;
import com.example.demo.service.dto.AccountRequestDto;
import com.example.demo.service.dto.ArticleRequestDto;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(value="local")
public class LocalRunnerConfig implements ApplicationRunner {
    private AccountService accountService;
    private ArticleService articleService;
    private CategoryRepository categoryRepository;

    public LocalRunnerConfig(AccountService accountService, ArticleService articleService, CategoryRepository categoryRepository) {
        this.accountService = accountService;
        this.articleService = articleService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        AccountRequestDto account = new AccountRequestDto("김동규", "201412345", "user@email.com", "123");
        accountService.save(account);
        AccountRequestDto account2 = new AccountRequestDto("이상훈", "201412346", "user1@email.com", "123");
        accountService.save(account2);
        AccountRequestDto account3 = new AccountRequestDto("안태회", "2014123467", "user2@email.com", "123");
        accountService.save(account3);
        AccountRequestDto account4 = new AccountRequestDto("곽병선", "2014123468", "user3@email.com", "123");
        accountService.save(account4);

        Category category = Category.withName("학과 게시판");
        Category category1 = Category.withName("자유 게시판");
        Category category2 = Category.withName("선배의 조언");
        Category category3 = Category.withName("중고거래 게시판");
        Category category4 = Category.withName("홍보 게시판");

        Category save = categoryRepository.save(category);
        Category save1 = categoryRepository.save(category1);
        Category save2 = categoryRepository.save(category2);
        Category save3 = categoryRepository.save(category3);
        Category save4 = categoryRepository.save(category4);

        for (int i = 0; i < 100; i++) {
            ArticleRequestDto articleRequestDto = new ArticleRequestDto("제목" + i, "내용" + i);
            articleService.save(account.getEmail(), save.getId(), articleRequestDto);
        }

        for (int i = 100; i < 200; i++) {
            ArticleRequestDto articleRequestDto = new ArticleRequestDto("제목" + i, "내용" + i);
            articleService.save(account2.getEmail(), save1.getId(), articleRequestDto);
        }

        for (int i = 200; i < 300; i++) {
            ArticleRequestDto articleRequestDto = new ArticleRequestDto("제목" + i, "내용" + i);
            articleService.save(account3.getEmail(), save2.getId(), articleRequestDto);
        }

        for (int i = 300; i < 400; i++) {
            ArticleRequestDto articleRequestDto = new ArticleRequestDto("제목" + i, "내용" + i);
            articleService.save(account4.getEmail(), save3.getId(), articleRequestDto);
        }

        for (int i = 400; i < 500; i++) {
            ArticleRequestDto articleRequestDto = new ArticleRequestDto("제목" + i, "내용" + i);
            articleService.save(account.getEmail(), save4.getId(), articleRequestDto);
        }
    }
}
