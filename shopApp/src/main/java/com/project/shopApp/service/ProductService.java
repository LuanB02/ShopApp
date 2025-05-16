package com.project.shopApp.service;

import com.project.shopApp.dtos.ProductRequestDto;
import com.project.shopApp.dtos.response.ProductResponseDto;
import com.project.shopApp.dtos.ProductImageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto productReqeustDto);

    ProductResponseDto getProductById(long id);

    Page<ProductRequestDto> getAllProducts(PageRequest pageRequest);

    ProductResponseDto updateProduct(long id, ProductRequestDto productResponseDto);

    void deleteProduct(long id);

    boolean existsByName(String name);

    ProductImageDto createProductImage(long productId, ProductImageDto productImageDto);
}
