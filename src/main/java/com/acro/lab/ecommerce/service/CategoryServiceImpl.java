package com.acro.lab.ecommerce.service;

import com.acro.lab.ecommerce.entity.Category;
import com.acro.lab.ecommerce.exception.EcommerceException;
import com.acro.lab.ecommerce.repository.CategoryRepository;
import com.acro.lab.ecommerce.request.CategoryRequest;
import com.acro.lab.ecommerce.response.CategoryResponse;
import com.acro.lab.ecommerce.responsemethod.CommonResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;
    private final CommonResponseMapper commonResponseMapper;

    public CategoryServiceImpl(@Autowired CategoryRepository categoryRepository,
                               @Autowired CommonResponseMapper commonResponseMapper) {
        this.categoryRepository = categoryRepository;
        this.commonResponseMapper = commonResponseMapper;
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        LOGGER.info("Receiveed request to category");
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());
        category.setCreatedAt(categoryRequest.getCreatedAt());
        category.setModifiedAt(categoryRequest.getModifiedAt());
        category.setStatus(categoryRequest.getStatus());
        Category saveCategory = categoryRepository.save(category);
        LOGGER.info("saved successfully", categoryRequest.getName());
        return commonResponseMapper.getCategoryResponse(category);
    }


    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        LOGGER.info("update category by id");
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            Category existingcategory = category.get();
            existingcategory.setName(categoryRequest.getName());
            existingcategory.setDescription(categoryRequest.getDescription());
            existingcategory.setCreatedAt(categoryRequest.getCreatedAt());
            existingcategory.setModifiedAt(categoryRequest.getModifiedAt());
            existingcategory.setStatus(categoryRequest.getStatus());
            Category updatedCategory = categoryRepository.save(existingcategory);
            LOGGER.info("Updated Category Successfully");
            return commonResponseMapper.getCategoryResponse(updatedCategory);
        } else {
            throw new EcommerceException("Category not found");
        }

    }

    public CategoryResponse findCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            Category capturedcategory = category.get();
            return commonResponseMapper.getCategoryResponse(capturedcategory);
        } else {
            throw new EcommerceException("Category not found");
        }

    }


    public boolean deleteCategoryById(Long id) {
    Optional<Category>category=categoryRepository.findById(id);
     if(category.isEmpty()){
         LOGGER.error("failed to delete categoryById");
         throw new EcommerceException("Category not found");
     }else{
         try{
             Category callCategory=category.get();
             categoryRepository.deleteById(id);
             return true;
         }catch(Exception e){
             LOGGER.error("Failed to delete CategoryById ");
         }
     }
     return false;
    }


}
