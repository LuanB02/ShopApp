package com.project.shopApp.mapper;

import com.project.shopApp.dtos.ProductImageDto;
import com.project.shopApp.models.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductImageMapping {
    ProductImageMapping INSTANCE = Mappers.getMapper(ProductImageMapping.class);
    @Mapping(target = "productId", source = "product.id")
    ProductImageDto toDto(ProductImage productImage);

    List<ProductImageDto> toListDto(List<ProductImage> productImages);
    @Mapping(target = "id", ignore = true)
    ProductImage toModel(ProductImageDto productImageDto);

    List<ProductImage> toListModel(List<ProductImageDto> productImageDtos);
}