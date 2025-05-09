package com.project.shopApp.mapper;

import com.project.shopApp.dtos.CategoryDto;
import com.project.shopApp.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapping {
    CategoryMapping INSTANCE = Mappers.getMapper(CategoryMapping.class);
    CategoryDto toDto(Category category);

    List<CategoryDto> toListDto(List<Category> categories);

    Category toModel(CategoryDto categoryDto);

    List<Category> toListModel(List<CategoryDto> categoryDtos);
}
