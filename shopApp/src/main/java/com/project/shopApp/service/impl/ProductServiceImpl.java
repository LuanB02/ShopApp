package com.project.shopApp.service.impl;

import com.project.shopApp.common.exceptions.CheckEmptyCategoryException;
import com.project.shopApp.common.exceptions.CheckEmptyProductException;
import com.project.shopApp.common.exceptions.CheckEmptyProductsException;
import com.project.shopApp.common.exceptions.CheckLengthImagesException;
import com.project.shopApp.common.logger.AbstractMessage;
import com.project.shopApp.dtos.ProductImageDto;
import com.project.shopApp.dtos.ProductRequestDto;
import com.project.shopApp.dtos.response.ProductResponseDto;
import com.project.shopApp.mapper.ProductImageMapping;
import com.project.shopApp.mapper.ProductMapping;
import com.project.shopApp.models.Category;
import com.project.shopApp.models.Product;
import com.project.shopApp.models.ProductImage;
import com.project.shopApp.repository.CategoryRepository;
import com.project.shopApp.repository.ProductImageRepository;
import com.project.shopApp.repository.ProductRepository;
import com.project.shopApp.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl extends AbstractMessage implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductImageRepository productImageRepository;

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Category category = categoryRepository.getCategoryById(productRequestDto.getCategoryId());

        if (category != null) {
            Product product = productRepository.save(ProductMapping.INSTANCE.toModel(productRequestDto));
            product.setCategory(category);
            logger.info("Product is created: {}", getMessage("ItemCreate"));
            return ProductMapping.INSTANCE.toDto(product);
        }
        logger.error("Product is not found: {}", getMessage("ItemNotFound"));
        throw new CheckEmptyProductException(productRequestDto.getCategoryId());
    }

    @Override
    public ProductResponseDto getProductById(long id) {
        logger.info("Product is found: {}", getMessage("ItemFound"));
        return ProductMapping.INSTANCE.toDto((
                productRepository.findById(id).orElseThrow(() -> {
                    logger.error("Product is not found: {}", getMessage("ItemNotFound"));
                    return new CheckEmptyProductException(id);
                })));
    }

    @Override
    public Page<ProductRequestDto> getAllProducts(PageRequest pageRequest) {
        Page<Product> productPage = productRepository.findAll(pageRequest);

        if (productPage.isEmpty()) {
            logger.error("Product is not found: {}", getMessage("ItemNotFound"));
            throw new CheckEmptyProductsException();
        }

        logger.info("Product is found: {}", getMessage("ItemFound"));

        return productPage.map(ProductMapping.INSTANCE::toProductEntity);
    }

    @Override
    public ProductResponseDto updateProduct(long id, ProductRequestDto productRequestDto) {

        Product product = productRepository.findById(id).orElseThrow(() -> new CheckEmptyProductException(id));
        Category category = categoryRepository.findById(
                productRequestDto.getCategoryId()).orElseThrow(() -> new CheckEmptyCategoryException(productRequestDto.getCategoryId()));
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
            throw new CheckEmptyProductException(id);
        }
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

    @Override
    public ProductImageDto createProductImage(long productId, ProductImageDto productImageDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CheckEmptyProductException(productId));
        ProductImage productImage = ProductImage
                .builder()
                .product(product)
                .imageUrl(productImageDto.getImageUrl())
                .build();

        int size = productImageRepository.findByProductId(productId).size();
        if (size >= 5) {
            throw new CheckLengthImagesException();
        }
        return ProductImageMapping.INSTANCE.toDto(productImageRepository.save(productImage));
    }
}
