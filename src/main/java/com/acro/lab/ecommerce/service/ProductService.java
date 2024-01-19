package com.acro.lab.ecommerce.service;

import com.acro.lab.ecommerce.request.ProductRequest;
import com.acro.lab.ecommerce.response.ProductResponse;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    

    ProductResponse updateProduct(Long id, ProductRequest productRequest);


    ProductResponse getByProductId(Long id);


    Boolean deleteProductById(Long id);
}
