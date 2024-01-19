package com.acro.lab.ecommerce.controller;

import com.acro.lab.ecommerce.exceptions.EcommerceException;
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
@RequestMapping("/api/category")
public class CategoryController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    private final CategoryService categoryService;
    public CategoryController(@Autowired CategoryService categoryService){
        this.categoryService=categoryService;
    }
    @PostMapping("/")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody @Valid @NotNull CategoryRequest categoryRequest)
        throws EcommerceException {
        try {
            CategoryResponse categoryResponse = categoryService.createCategory(categoryRequest);
            if(categoryResponse!=null){
                return ResponseEntity.ok(categoryResponse);
            }
        }
        catch(Exception e){
                logger.error("category creation failed",e);
                throw new EcommerceException("category creation failed",e);
            }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable("id") Long id,
                                                     @RequestBody @Valid CategoryRequest categoryRequest) {
        if (categoryRequest != null) {
            CategoryResponse categoryRes = categoryService.updatedCategory(id, categoryRequest);
            if (categoryRes != null) {
                return ResponseEntity.ok(categoryRes);
            }
        }
        logger.error("Update Failed");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable("id") Long id){
        CategoryResponse response=categoryService.findByCategoryId(id);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCategoryById(@PathVariable Long id) {
        Boolean result = categoryService.deleteCategoryById(id);
        logger.info("Deleted Successfully");
        return ResponseEntity.ok(result);
    }
}
