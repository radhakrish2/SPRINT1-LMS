package com.learnhub.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learnhub.dto.CategoryDto;
import com.learnhub.entity.Category;
import com.learnhub.exception.ResourceNotFoundException;
import com.learnhub.mapper.CategoryMapper;
import com.learnhub.repository.CategoryRepository;
import com.learnhub.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        if (categoryRepository.existsByName(categoryDto.getName())) {
            throw new IllegalArgumentException(
                    "Category already exists with name : "
                            + categoryDto.getName());
        }

        Category category = categoryMapper.toEntity(categoryDto);

        category = categoryRepository.save(category);

        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto updateCategory(Long categoryId,
                                      CategoryDto categoryDto) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id : "
                                        + categoryId));

        // Check duplicate name
        if (!category.getName().equalsIgnoreCase(categoryDto.getName())
                && categoryRepository.existsByName(categoryDto.getName())) {

            throw new IllegalArgumentException(
                    "Category already exists with name : "
                            + categoryDto.getName());
        }

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        category = categoryRepository.save(category);

        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id : "
                                        + categoryId));

        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto getCategoryByName(String categoryName) {

        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with name : "
                                        + categoryName));

        return categoryMapper.toDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {

        return categoryMapper.toDtoList(
                categoryRepository.findAll());
    }

    @Override
    public void deleteCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id : "
                                        + categoryId));

        // Prevent deleting category if courses exist
        if (category.getCourses() != null &&
                !category.getCourses().isEmpty()) {

            throw new IllegalStateException(
                    "Cannot delete category. It contains "
                            + category.getCourses().size()
                            + " course(s).");
        }

        categoryRepository.delete(category);
    }

    @Override
    public boolean existsByName(String categoryName) {

        return categoryRepository.existsByName(categoryName);
    }

    @Override
    public Long getCategoryCount() {

        return categoryRepository.count();
    }

}