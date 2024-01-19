package com.acro.lab.ecommerce.service;

import com.acro.lab.ecommerce.entity.Category;
import com.acro.lab.ecommerce.request.CategoryRequest;
import com.acro.lab.ecommerce.response.CategoryResponse;

import java.util.Optional;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);

    CategoryResponse findByCategoryId(Long categoryId);

    Optional<Category> getByCategoryId(Long categoryId);

    CategoryResponse updatedCategory(Long id, CategoryRequest categoryRequest);

    Boolean deleteCategoryById(Long id);
}
