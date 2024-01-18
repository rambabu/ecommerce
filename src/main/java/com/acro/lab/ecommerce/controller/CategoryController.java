package com.acro.lab.ecommerce.controller;

import com.acro.lab.ecommerce.exception.EcommerceException;
import com.acro.lab.ecommerce.request.CategoryRequest;
import com.acro.lab.ecommerce.response.CategoryResponse;
import com.acro.lab.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/category")
public class CategoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    private final CategoryService categoryService;

    public CategoryController(@Autowired CategoryService categoryservice) {
        this.categoryService = categoryservice;
    }

    @PostMapping("/")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody @Valid @NotNull CategoryRequest categoryRequest)
            throws EcommerceException {
        try {
            CategoryResponse categoryResponse = categoryService.createCategory(categoryRequest);
            //return ResponseEntity.ok(categoryResponse);
        } catch (Exception e) {
            LOGGER.error("failed to create category name {}", categoryRequest.getName());
            throw new EcommerceException("Creation failed ", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @RequestBody @Valid @NotNull CategoryRequest categoryRequest)
            throws EcommerceException {
        try {
            CategoryResponse categoryResponse = categoryService.updateCategory(id, categoryRequest);
            //return ResponseEntity.ok(categoryResponse);
        } catch (Exception e) {
            LOGGER.error("failed to update category name {}", categoryRequest.getName());
            throw new EcommerceException("Creation failed", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping({"/id"})
    public ResponseEntity<CategoryResponse> findCategoryById(@PathVariable Long id) throws EcommerceException {
        try {
            CategoryResponse categoryResponse = categoryService.findCategoryById(id);
        } catch (Exception e) {
            LOGGER.error("Failed to find categoryId " + id);
            throw new EcommerceException("Failed to find CategoryId " + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) throws EcommerceException {
        try {
            boolean res = categoryService.deleteCategoryById(id);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            LOGGER.error("failed to delete categoryById" + id);
            throw new EcommerceException("failed to delete categoryById" + id);
        }
    }
}

