package com.spring.capstone.backend.domain.category;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategoryList() {
        List<Category> all = categoryRepository.findAll();
        System.out.println(all);
        return all;
    }

    public Category getCategory(long id) {
        return categoryRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
