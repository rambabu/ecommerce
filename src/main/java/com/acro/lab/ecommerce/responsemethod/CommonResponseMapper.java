package com.acro.lab.ecommerce.responsemethod;

import com.acro.lab.ecommerce.entity.Category;
import com.acro.lab.ecommerce.entity.Product;
import com.acro.lab.ecommerce.response.CategoryResponse;
import com.acro.lab.ecommerce.response.ProductResponse;

public class CommonResponseMapper {

    public ProductResponse getProductResponse(Product product){
        ProductResponse productResponse=new ProductResponse();
        productResponse.setProductId(product.getId());
        productResponse.setProductName(product.getProductName());
        productResponse.setProductDescription(product.getProductDescription());
        productResponse.setCategoryId(product.getCategoryId());
        return productResponse;
    }
    public CategoryResponse getCategoryResponse(Category category){
        CategoryResponse categoryResponse=new CategoryResponse();
        categoryResponse.setCategoryId(category.getId());
        categoryResponse.setName(category.getName());
        categoryResponse.setDescription(category.getDescription());
        categoryResponse.setCreatedAt(category.getCreatedAt());
        categoryResponse.setModifiedAt(category.getModifiedAt());
         return categoryResponse;
    }

}
