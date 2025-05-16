package com.project.shopApp.controller;

import com.github.javafaker.Faker;
import com.project.shopApp.common.exceptions.*;
import com.project.shopApp.dtos.response.GetProductsResponseDto;
import com.project.shopApp.dtos.ProductImageDto;
import com.project.shopApp.dtos.ProductRequestDto;
import com.project.shopApp.dtos.response.ProductResponseDto;
import com.project.shopApp.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<?> getAllProduct(@RequestParam int page, @RequestParam int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by("createAt").ascending());
        Page<ProductRequestDto> pageRequests = productService.getAllProducts(pageRequest);
        int totalPages = pageRequests.getTotalPages();
        int number = pageRequests.getNumber();
        if (number >= totalPages) {
            throw new CheckPagesException();
        }
        List<ProductRequestDto> products = pageRequests.getContent();

        return ResponseEntity.ok(GetProductsResponseDto.builder()
                .products(products)
                .totalPages(totalPages)
                .build());
//        return ResponseEntity.ok(pageRequests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        ProductResponseDto productResponseDto = productService.getProductById(id);
        return ResponseEntity.ok(productResponseDto);
    }

    @PostMapping(value = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> insertProductWithImage(@Valid @ModelAttribute ProductResponseDto productResponseDto,
                                                    BindingResult bindingResult
    ) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> error = bindingResult.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();

                return ResponseEntity.badRequest().body(error);
            }
            MultipartFile file = productResponseDto.getFile();
            if (file != null) {
                if (file.getSize() > 10 * 1024 * 1024) {
                    throw new ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE, "File is too large! Maximum is 10MB");
                }
                String contentType = file.getContentType();
                System.out.println("Content: " + contentType);
                if (contentType == null || !contentType.startsWith("image/")) {
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("File must be an image");
                }

                storeFile(file);
            }
            return ResponseEntity.ok("This is post method with productName: " + productResponseDto);
        } catch (IOException | ResponseStatusException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> insertProductWithImages(
            @Valid @RequestBody ProductRequestDto productResquestDto,
            BindingResult bindingResult
    ) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> error = bindingResult.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();

                return ResponseEntity.badRequest().body(error);
            }
            ProductResponseDto productResponseDto1 = productService.createProduct(productResquestDto);

            return ResponseEntity.ok(productResponseDto1);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "uploads/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImages(
            @Valid @PathVariable Long id,
            @Valid @RequestParam("files") List<MultipartFile> files
    ) throws IOException {
        ProductResponseDto productResponseDto = productService.getProductById(id);
        files = files == null ? new ArrayList<>() : files;

        if (files.size() > 5) {
            throw new CheckLengthImagesException();
        }
        List<ProductImageDto> productImageDtos = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.getSize() == 0) {
                continue;
            }
            if (file != null) {
                if (file.getSize() > 10 * 1024 * 1024) {
                    throw new CheckSizeImagesException();
                }
                String contentType = file.getContentType();
                System.out.println("Content: " + contentType);
                if (contentType == null || !contentType.startsWith("image/")) {
                    throw new CheckTypeOfImageException();
                }

                String fileName = storeFile(file);
                ProductImageDto productImageDto = productService.createProductImage(
                        productResponseDto.getId(),
                        ProductImageDto.builder().imageUrl(fileName).build()
                );
                productImageDtos.add(productImageDto);
            }
        }
        return ResponseEntity.ok().body(productImageDtos);
    }

    private String storeFile(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String uniqueFilename = UUID.randomUUID().toString() + "_" + filename;

        Path uploadDir = Paths.get("uploads");

        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
        Path destination = Paths.get(uploadDir.toString(), uniqueFilename);

        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        return uniqueFilename;
    }

    //    {
//        "name": "dien thoai",
//            "price": 888,
//            "thumbnail": "",
//            "description": "",
//            "category_id": 1
//    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable long id, @RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto getResponseById = productService.getProductById(id);

        if (getResponseById != null){

            ProductResponseDto productResponseDto = productService.updateProduct(getResponseById.getId(), productRequestDto);
//            ProductResponseDto.builder().
            return ResponseEntity.ok(productResponseDto);
        }
        throw new CheckEmptyProductException(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("This is delete method with id = " + id);
    }

//    @PostMapping("/fakeData")
    private ResponseEntity<?> createFakeData(){
        Faker faker = new Faker();
        for (int i = 0; i < 10000; i++){
            String productName = faker.commerce().productName();
            if(productService.existsByName(productName)){
                continue;
            }
            List<Long> categoryIds = Arrays.asList(4L, 5L, 11L, 12L, 13L);
            Long randomCategoryId = categoryIds.get(new Random().nextInt(categoryIds.size()));
            ProductRequestDto productRequestDto = ProductRequestDto.builder()
                    .name(productName)
                    .price((float) faker.number().numberBetween(10, 1000000))
                    .description(faker.lorem().sentence())
                    .categoryId(randomCategoryId)
                    .thumbnail("")
                    .build();
            productService.createProduct(productRequestDto);
        }
        return ResponseEntity.ok(("Created Fake data"));
    }
}
