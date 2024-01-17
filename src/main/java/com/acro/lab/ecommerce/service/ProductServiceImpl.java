package com.acro.lab.ecommerce.service;

import com.acro.lab.ecommerce.entity.Product;
import com.acro.lab.ecommerce.repository.ProductRepository;
import com.acro.lab.ecommerce.request.ProductRequest;
import com.acro.lab.ecommerce.response.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        log.info("received create product request");
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(product.getDescription());
        product.setPrice(product.getPrice());
        product.setCategory(product.getCategory());
        Product productOne = productRepository.save(product);
        log.info("Saved Successfully");
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(productOne, productResponse);
        return productResponse;
    }

}
