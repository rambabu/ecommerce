package com.acro.lab.ecommerce.service;

import com.acro.lab.ecommerce.request.ProductRequest;
import com.acro.lab.ecommerce.response.ProductResponse;

public interface ProductService {
    public ProductResponse createProduct(ProductRequest productRequest);

    public ProductResponse updateProduct(Long id, ProductRequest productRequest);

    public ProductResponse findProductById(Long id);

    public boolean deleteProductById(Long id);

}
