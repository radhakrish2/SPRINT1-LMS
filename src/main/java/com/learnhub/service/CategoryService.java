package com.learnhub.service;

import java.util.List;

import com.learnhub.dto.CategoryDto;

public interface CategoryService {

    // Create a new category
    CategoryDto createCategory(CategoryDto categoryDto);

    // Update an existing category
    CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto);

    // Get category by ID
    CategoryDto getCategoryById(Long categoryId);

    // Get category by name
    CategoryDto getCategoryByName(String categoryName);

    // Get all categories
    List<CategoryDto> getAllCategories();

    // Delete category
    void deleteCategory(Long categoryId);

    // Check if category exists
    boolean existsByName(String categoryName);

    // Get total number of categories
    Long getCategoryCount();

}