package com.spring.capstone.backend.web;

import com.spring.capstone.backend.web.argumentresolver.AccountSessionArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private AccountSessionArgumentResolver accountSessionArgumentResolver;

    public WebMvcConfig(AccountSessionArgumentResolver accountSessionArgumentResolver) {
        this.accountSessionArgumentResolver = accountSessionArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(accountSessionArgumentResolver);
    }
}
