package com.acro.lab.ecommerce;

import com.acro.lab.ecommerce.entity.Category;
import com.acro.lab.ecommerce.entity.Product;
import com.acro.lab.ecommerce.response.CategoryResponse;
import com.acro.lab.ecommerce.response.ProductResponse;
import org.springframework.beans.BeanUtils;


public class CommonResponseMapper {
    public static CategoryResponse convertToCategoryResponse(Category category ) {
        CategoryResponse categoryResponse = new CategoryResponse();
        BeanUtils.copyProperties(category, categoryResponse);  //It will copy the properties with same Name.
        return categoryResponse;
    }
    public static ProductResponse convertToProductResponse(Product product){
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(product, productResponse);
        productResponse.setCategoryId(product.getId());
        return productResponse;
    }


}
