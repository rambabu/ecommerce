package com.acro.lab.ecommerce.service;

import com.acro.lab.ecommerce.CommonResponseMapper;
import com.acro.lab.ecommerce.dto.ProductProjection;
import com.acro.lab.ecommerce.entity.Category;
import com.acro.lab.ecommerce.entity.Product;
import com.acro.lab.ecommerce.exceptions.EcommerceException;
import com.acro.lab.ecommerce.repository.ProductRepository;
import com.acro.lab.ecommerce.request.ProductRequest;
import com.acro.lab.ecommerce.response.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;


    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;

    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        log.info("received create product request");
        try {
            Optional<Category> category = categoryService.getByCategoryId(productRequest.getCategoryId());
            if (category.isPresent()) {
                Category categoryOne = category.get();
                Product product = new Product();
                product.setName(productRequest.getName());
                product.setDescription(productRequest.getDescription());
                product.setPrice(productRequest.getPrice());
                product.setCategory(categoryOne);
                product.setQuantity(productRequest.getQuantity());
                Product productOne = productRepository.save(product);
                return CommonResponseMapper.convertToProductResponse(productOne);
            } else {
                log.error("Category not found");
                throw new EcommerceException("product not created");
            }
        } catch (Exception exception) {
            log.error("Unable create Product");
            throw new EcommerceException("product not created", exception);
        }
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product productOne = product.get();
            BeanUtils.copyProperties(productRequest, product);
            Product savedProduct = productRepository.save(productOne);
            log.info("category saved successfully{}", savedProduct);
            return CommonResponseMapper.convertToProductResponse(savedProduct);
        }
        log.error("product not found");
        throw new EcommerceException("Error processing product update");
    }

    @Override
    public ProductResponse getByProductId(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product productOne = product.get();
            return CommonResponseMapper.convertToProductResponse(productOne);
        } else {
            throw new EcommerceException("Product not found");
        }
    }
    @Override
    public Boolean deleteProductById(Long id) {
        try{
            Optional<Product> product = productRepository.findById(id);
            if(product.isPresent()){
                Product productOne = product.get();
                productRepository.deleteById(id);
                log.info("Product deleted successfully {}", productOne);
                return true;
            }
        }
        catch(Exception exception){
            log.error("Unable to delete the product",exception);
        }
        throw new EcommerceException("Unable to Delete");
    }
    @Override
    public List<ProductResponse> getAllProductsByCategoryId(Long categoryId) {
        List<ProductProjection> products =productRepository.findProductByCategoryId(categoryId);

        return products.stream().map(p -> {
            ProductResponse response = new ProductResponse();
            response.setCategoryId(p.getId());
            response.setCategoryName(p.getCategoryName());
            response.setName(p.getName());
            response.setPrice(p.getPrice());
            response.setDescription(p.getDescription());
            response.setId(p.getId());
            response.setQuantity(p.getQuantity());
            return response;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getProductsByName(String name) {
        List<Product> products =productRepository.findProductByName(name);
        return products.stream().map(CommonResponseMapper::convertToProductResponse).collect(Collectors.toList());


    }
}
