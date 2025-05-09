package com.project.shopApp.service;

import com.project.shopApp.dtos.CategoryDto;
import com.project.shopApp.models.Category;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto getCategoryById(long categoryId);

    List<CategoryDto> getAllCategories();

    CategoryDto updateCategory(long categoryId, CategoryDto categoryDto);

    void deleteCategory(long id);
}
