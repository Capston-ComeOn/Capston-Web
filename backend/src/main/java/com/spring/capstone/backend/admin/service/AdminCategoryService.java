package com.spring.capstone.backend.admin.service;

import com.spring.capstone.backend.domain.category.Category;
import com.spring.capstone.backend.domain.category.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminCategoryService {

    private CategoryRepository categoryRepository;

    public AdminCategoryService(CategoryRepository categoryRepository) {
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

