package com.learnhub.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.learnhub.dto.CategoryDto;
import com.learnhub.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto dto);

    List<CategoryDto> toDtoList(List<Category> categories);

}