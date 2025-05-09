package com.project.shopApp.service.impl;

import com.project.shopApp.common.exceptions.CheckEmptyItemException;
import com.project.shopApp.common.exceptions.CheckSizeImagesException;
import com.project.shopApp.dtos.ProductRequestDto;
import com.project.shopApp.dtos.ProductResponseDto;
import com.project.shopApp.dtos.ProductImageDto;
import com.project.shopApp.mapper.ProductImageMapping;
import com.project.shopApp.mapper.ProductMapping;
import com.project.shopApp.models.Category;
import com.project.shopApp.models.Product;
import com.project.shopApp.models.ProductImage;
import com.project.shopApp.repository.CategoryRepository;
import com.project.shopApp.repository.ProductImageRepository;
import com.project.shopApp.repository.ProductRepository;
import com.project.shopApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

//import static jdk.tools.jlink.internal.plugins.PluginsResourceBundle.getMessage;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductImageRepository productImageRepository;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Category category = categoryRepository.getCategoryById(productRequestDto.getCategoryId());

        if (category != null) {
            Product product = productRepository.save(ProductMapping.INSTANCE.toModel(productRequestDto));
            product.setCategory(category);

            return ProductMapping.INSTANCE.toDto(product);
        }
        throw new CheckEmptyItemException(productRequestDto.getCategoryId());
    }

    @Override
    public ProductResponseDto getProductById(long id) {
        return ProductMapping.INSTANCE.toDto((
                productRepository.findById(id).orElseThrow(() -> new CheckEmptyItemException(id)
                )));
    }

    @Override
    public Page<ProductResponseDto> getAllProducts(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest).map(ProductMapping.INSTANCE::toDto);
    }

    @Override
    public ProductResponseDto updateProduct(long id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new CheckEmptyItemException(id));
        Category category = categoryRepository.findById(
                productRequestDto.getCategoryId()).orElseThrow(() -> new CheckEmptyItemException(productRequestDto.getCategoryId()));
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setCategory(category);
        productRepository.save(product);
        return ProductMapping.INSTANCE.toDto(product);
    }

    @Override
    public void deleteProduct(long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.delete(product.get());
        } else {
            throw new CheckEmptyItemException(id);
        }
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

    @Override
    public ProductImageDto createProductImage(long productId, ProductImageDto productImageDto){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CheckEmptyItemException(productId));
        ProductImage productImage = ProductImage
                .builder()
                .product(product)
                .imageUrl(productImageDto.getImageUrl())
                .build();

        int size = productImageRepository.findByProductId(productId).size();
        if (size >= 5){
            throw new CheckSizeImagesException();
        }
        return ProductImageMapping.INSTANCE.toDto(productImageRepository.save(productImage));
    }
}
