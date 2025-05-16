package com.project.shopApp.service.impl;

import com.project.shopApp.common.exceptions.CheckDuplicateException;
import com.project.shopApp.common.exceptions.CheckEmptyCategoriesException;
import com.project.shopApp.common.exceptions.CheckEmptyCategoryException;
import com.project.shopApp.common.logger.AbstractMessage;
import com.project.shopApp.dtos.CategoryDto;
import com.project.shopApp.mapper.CategoryMapping;
import com.project.shopApp.models.Category;
import com.project.shopApp.repository.CategoryRepository;
import com.project.shopApp.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryServiceImpl extends AbstractMessage implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private void checkDuplicate(CategoryDto categoryDto) {
        List<Category> categories = categoryRepository.findAll();
        for (Category category1: categories) {
            if(Objects.equals(categoryDto.getName(), category1.getName())){
                logger.error("Category is duplicated: {}", getMessage("ItemDuplicated"));
                throw new CheckDuplicateException();
            }
        }
    }
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        checkDuplicate(categoryDto);
        Category category = categoryRepository.save(CategoryMapping.INSTANCE.toModel(categoryDto));
        logger.info("CategoryCreate: {}", getMessage("ItemCreate"));
        return CategoryMapping.INSTANCE.toDto(category);
    }

    @Override
    public CategoryDto getCategoryById(long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()){
            logger.error("ItemNotFound: {}", getMessage("ItemNotFound"));
            throw new CheckEmptyCategoryException(categoryId);
        }
        logger.info("CategoryFound: {}", getMessage("ItemFound"));
        return CategoryMapping.INSTANCE.toDto(category.get());
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()){
            logger.error("ItemNotFound: {}", getMessage("ItemNotFound"));
            throw new CheckEmptyCategoriesException();
        }
        logger.info("CategoriesFound: {}", getMessage("ItemFound"));
        return CategoryMapping.INSTANCE.toListDto(categories);
    }

    @Override
    public CategoryDto updateCategory(long categoryId, CategoryDto categoryDto) {
        checkDuplicate(categoryDto);
        Category category = categoryRepository.getCategoryById(categoryId);
        if (category != null) {
            category.setName(categoryDto.getName());

            logger.info("CategoryUpdate: {}", getMessage("ItemUpdate"));
            return CategoryMapping.INSTANCE.toDto(categoryRepository.save(category));
        } else {
            logger.error("ItemNotFound: {}", getMessage("ItemNotFound"));
            throw new CheckEmptyCategoryException(categoryId);
        }
    }

    @Override
    public void deleteCategory(long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            logger.info("CategoryDelete: {}", getMessage("ItemDelete"));
            categoryRepository.deleteById(category.get().getId());
        } else {
            logger.error("ItemNotFound: {}", getMessage("ItemNotFound"));
            throw new CheckEmptyCategoryException(id);
        }
    }
}
