package com.project.shopApp.mapper;

import com.project.shopApp.dtos.ProductRequestDto;
import com.project.shopApp.dtos.ProductResponseDto;
import com.project.shopApp.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapping {
    ProductMapping INSTANCE = Mappers.getMapper(ProductMapping.class);
//    @Mapping(source = "createAt", target = "createAt")
//    @Mapping(source = "updateAt", target = "updateAt")
//    @Mapping(source = "category.id", target = "categoryId")
//    @Mapping(source = "category", target = "category")
    @Mapping(source = "createAt", target = "createAt")
    @Mapping(source = "updateAt", target = "updateAt")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "category.id", target = "categoryId")
    ProductResponseDto toDto(Product product);

    ProductRequestDto toRequestDto(Product product);
    List<ProductResponseDto> toListDto(List<Product> products);
    @Mapping(source = "categoryId", target = "category.id")
    Product toModel(ProductRequestDto productRequestDto);

    List<Product> toListModel(List<ProductResponseDto> productResponseDtos);
}
