package com.spring.capstone.backend.service.dto;

import com.spring.capstone.backend.domain.category.Category;

public class CategoryResponse {

    private Long id;

    private String name;

    public static CategoryResponse withCategory(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }

    private CategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
