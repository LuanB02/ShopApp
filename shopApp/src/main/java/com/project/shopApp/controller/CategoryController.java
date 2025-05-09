package com.project.shopApp.controller;

import java.util.List;

import com.project.shopApp.repository.CategoryRepository;
import com.project.shopApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopApp.dtos.CategoryDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<?> getAllCategories(@RequestParam Integer page, @RequestParam Integer limit) {
        List<CategoryDto> categoryDtos = categoryService.getAllCategories();
        return ResponseEntity.ok(categoryDtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@Valid @PathVariable int id) {
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryDto);
    }

    @PostMapping()
    public ResponseEntity<?> insertCategory(@Valid @RequestBody CategoryDto categoryDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<String> error = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError:: getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(error);
        }
        CategoryDto categoryDto1 = categoryService.createCategory(categoryDto);
        return ResponseEntity.ok(categoryDto1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable long id,
                                                 @Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto categoryDto1 = categoryService.updateCategory(id, categoryDto);
        return ResponseEntity.ok("This is update method with id = " + id + "and name is " + categoryDto1.getName());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable long id) {

        categoryService.deleteCategory(id);
        return ResponseEntity.ok("This is delete method with id = " + id);
    }
}
