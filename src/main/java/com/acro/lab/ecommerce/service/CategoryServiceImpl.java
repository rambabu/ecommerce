package com.acro.lab.ecommerce.service;

import com.acro.lab.ecommerce.CommonResponseMapper;
import com.acro.lab.ecommerce.entity.Category;
import com.acro.lab.ecommerce.exceptions.EcommerceException;
import com.acro.lab.ecommerce.repository.CategoryRepository;
import com.acro.lab.ecommerce.request.CategoryRequest;
import com.acro.lab.ecommerce.response.CategoryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    public CategoryServiceImpl(@Autowired CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }
    @Transactional
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        log.info("Received category request {}", categoryRequest);
        try {
            CategoryResponse categoryResponse;
            if (categoryRequest != null) {
                Category category = new Category();
                BeanUtils.copyProperties(categoryRequest, category);
                Category categoryOne = categoryRepository.save(category);
                log.info("saved successfully");
                return CommonResponseMapper.convertToCategoryResponse(categoryOne);
            } else {
                log.error("create failed");
                throw new EcommerceException("Unable to create category");
            }
        }catch(Exception e) {
            log.error("Category creation failed");
            throw new EcommerceException("Unable to create Category");
        }
    }
    @Override
    public CategoryResponse findByCategoryId(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            Category categoryOne = category.get();
            return CommonResponseMapper.convertToCategoryResponse(categoryOne);
        } else {
            throw new EcommerceException("Category not found");
        }
    }
    @Override
    public Optional<Category> getByCategoryId(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public CategoryResponse updatedCategory(Long id, CategoryRequest categoryRequest) {
        Optional<Category> category=categoryRepository.findById(id);
            if(category.isPresent()){
                Category categoryOne = category.get();
                BeanUtils.copyProperties(categoryRequest,category);
                Category savedCategory = categoryRepository.save(categoryOne);
                log.info("category saved successfully{}",savedCategory);
                return CommonResponseMapper.convertToCategoryResponse(savedCategory);
            }
            throw new EcommerceException("Error processing Category update");
    }
    @Override
    public Boolean deleteCategoryById(Long id) {
        try{
            Optional<Category> category = categoryRepository.findById(id);
            if(category.isPresent()){
                categoryRepository.deleteById(id);
                log.info("Category deleted successfully");
                return true;
            }
        }
        catch(Exception exception){
            log.error("Unable to delete the category",exception);
        }
        throw new EcommerceException("Unable to Delete");
    }

}
