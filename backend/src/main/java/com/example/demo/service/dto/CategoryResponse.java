package com.example.demo.service.dto;


import com.example.demo.domain.category.Category;

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
