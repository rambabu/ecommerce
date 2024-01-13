package com.acro.lab.ecommerce.service;

import com.acro.lab.ecommerce.request.ProductRequest;
import com.acro.lab.ecommerce.response.ProductResponse;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
}
