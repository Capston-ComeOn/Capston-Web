package com.example.demo.service;

import com.example.demo.domain.category.Category;
import com.example.demo.domain.category.CategoryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategoryList() {
        return categoryRepository.findAll(Sort.by(Sort.Order.asc("id")));
    }

    public Category getCategory(long id) {
        return categoryRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
