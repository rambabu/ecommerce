package com.acro.lab.ecommerce.controller;

import com.acro.lab.ecommerce.exceptions.EcommerceException;
import com.acro.lab.ecommerce.request.ProductRequest;
import com.acro.lab.ecommerce.response.ProductResponse;
import com.acro.lab.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@Slf4j
public class ProductController {

    private final ProductService productService;


    public ProductController(@Autowired ProductService productService){
        this.productService=productService;
    }
    @PostMapping("/")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest productRequest)
       {
           try {
               if (productRequest != null){
                   ProductResponse productResponse=productService.createProduct(productRequest);
                   return ResponseEntity.ok(productResponse);
               }
           }
           catch(Exception e){
               log.error("create failed", e);
               throw new EcommerceException("creation failed",e);
           }
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id,
                                                        @RequestBody @Valid ProductRequest productRequest) {
        if (productRequest != null) {
            ProductResponse productRes = productService.updateProduct(id, productRequest);
                return ResponseEntity.ok(productRes);
            }
        log.error("Update Failed");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id){
        ProductResponse response=productService.getByProductId(id);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProductById(@PathVariable Long id) {
        Boolean result = productService.deleteProductById(id);
        log.info("Deleted Successfully");
        return ResponseEntity.ok(result);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponse>> getAllProductsByCategoryId(@PathVariable Long categoryId) {
        List<ProductResponse> allProducts = productService.getAllProductsByCategoryId(categoryId);
        if (allProducts != null) return ResponseEntity.ok(allProducts);
        return ResponseEntity.ok(Collections.emptyList());
    }
    @GetMapping("/name/{name}")
    private ResponseEntity<List<ProductResponse>> getProductsByName(@PathVariable String name){
       List<ProductResponse> response = productService.getProductsByName(name);
       if(response!=null)
        return ResponseEntity.ok(response);
       return ResponseEntity.ok(Collections.emptyList());
    }
    }

