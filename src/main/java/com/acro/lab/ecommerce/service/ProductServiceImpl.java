package com.acro.lab.ecommerce.service;

import com.acro.lab.ecommerce.entity.Category;
import com.acro.lab.ecommerce.entity.Product;
import com.acro.lab.ecommerce.exception.EcommerceException;
import com.acro.lab.ecommerce.repository.CategoryRepository;
import com.acro.lab.ecommerce.repository.ProductRepository;
import com.acro.lab.ecommerce.request.ProductRequest;
import com.acro.lab.ecommerce.response.ProductResponse;
import com.acro.lab.ecommerce.responsemethod.CommonResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements  ProductService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CommonResponseMapper commonResponseMapper;

    public ProductServiceImpl(@Autowired ProductRepository productRepository,
                            @Autowired CategoryRepository categoryRepository, @Autowired CommonResponseMapper commonResponseMapper) {

        this.productRepository= productRepository;
        this.categoryRepository = categoryRepository;
        this.commonResponseMapper = commonResponseMapper;
    }


    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        LOGGER.info("Received request to Product");
        Product product = new Product();
        Optional<Category> category = categoryRepository.findById(productRequest.getCategoryId());
        if (category.isPresent()) {
            Category categoryOne = category.get();
            product.setProductName(productRequest.getProductName());
            product.setProductDescription(productRequest.getProductDescription());
            product.setCategory(category.get());
            Product productOne=productRepository.save(product);
            //convert owner to ownerResponse
            LOGGER.info("Successfully saved Product {}", productRequest.getProductName());
            return commonResponseMapper.getProductResponse(productOne);

        } else {
            throw new EcommerceException("Product not created");
        }

    }



    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Optional<Product> product=productRepository.findById(id);
        if(product.isPresent()){
            Product existingProduct=product.get();
            existingProduct.setProductName(productRequest.getProductName());
            existingProduct.setProductDescription(productRequest.getProductDescription());
           // existingProduct.setCategoryId(productRequest.getCategoryId());
            Product  updatedProduct=productRepository.save(existingProduct);
            LOGGER.info("Updated Product Successfully");
            return commonResponseMapper.getProductResponse(updatedProduct);
        }
           throw new EcommerceException("Product not found");
    }

    @Override
    public ProductResponse findProductById(Long id) {
        Optional<Product> product=productRepository.findById(id);
        if(product.isPresent()){
            Product capturedProduct=product.get();
            return commonResponseMapper.getProductResponse(capturedProduct);
        }
            throw new EcommerceException("Product not found");
    }
    @Override
    public boolean deleteProductById(Long id) {
        Optional<Product> product=productRepository.findById(id);
        if(product.isEmpty()){
            LOGGER.error("failed to delete ProductById");
            throw new EcommerceException("Product not found");
        }else{
            try{
                Product callProduct=product.get();
                productRepository.deleteById(id);
                return true;
            }catch(Exception e){
                LOGGER.error("Failed to deleted Product ById");
            }
        }
        return false;
    }
}
