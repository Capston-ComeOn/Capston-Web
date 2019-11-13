package com.example.demo.web.configs;

import com.example.demo.domain.category.Category;
import com.example.demo.domain.category.CategoryRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Profile(value = "prod")
public class ProdRunnerConfig  implements ApplicationRunner {

    private CategoryRepository categoryRepository;

    public ProdRunnerConfig(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String categories[] = {"학과 게시판", "자유 게시판", "선배의 조언", "중고거래 게시판", "홍보 게시판"};
        List<Category> collect = Arrays.stream(categories)
                .map(c -> Category.withName(c))
                .collect(Collectors.toList());
        categoryRepository.saveAll(collect);

    }
}