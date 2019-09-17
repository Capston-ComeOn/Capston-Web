package com.spring.capstone.backend.web.configs;

import com.spring.capstone.backend.web.argumentresolver.CurrentAccountArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private CurrentAccountArgumentResolver currentAccountArgumentResolver;

    public WebMvcConfig(CurrentAccountArgumentResolver currentAccountArgumentResolver) {
        this.currentAccountArgumentResolver = currentAccountArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentAccountArgumentResolver);
    }
}
