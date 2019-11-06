package com.spring.capstone.backend.service;

import com.spring.capstone.backend.domain.category.Category;
import com.spring.capstone.backend.domain.category.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategoryList() {
        return categoryRepository.findAll();
    }

    public Category getCategory(long id) {
        return categoryRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
