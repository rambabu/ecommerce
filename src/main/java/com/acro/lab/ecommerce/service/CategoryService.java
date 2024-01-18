package com.acro.lab.ecommerce.service;

import com.acro.lab.ecommerce.request.CategoryRequest;
import com.acro.lab.ecommerce.response.CategoryResponse;

public interface CategoryService {
    public CategoryResponse createCategory(CategoryRequest categoryRequest);

    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest);

    public CategoryResponse findCategoryById(Long id);

   public boolean deleteCategoryById(Long id);

}
