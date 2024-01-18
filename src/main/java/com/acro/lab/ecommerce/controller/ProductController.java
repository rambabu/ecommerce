package com.acro.lab.ecommerce.controller;

import com.acro.lab.ecommerce.exception.EcommerceException;
import com.acro.lab.ecommerce.request.ProductRequest;
import com.acro.lab.ecommerce.response.ProductResponse;
import com.acro.lab.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product")
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(@Autowired ProductService productService) {

        this.productService = productService;
    }

    @PostMapping("/")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid @NotNull ProductRequest productRequest)
            throws EcommerceException {
        try {
            ProductResponse productResponse = productService.createProduct(productRequest);
           // return ResponseEntity.ok(productResponse);
        } catch (Exception e) {
            LOGGER.error("failed to create product name {} ", productRequest.getProductName());
            throw new EcommerceException("Creation failed ", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }



    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id,
                                                         @RequestBody @Valid @NotNull ProductRequest productRequest) throws EcommerceException {
        try {
            ProductResponse productResponse = productService.updateProduct(id, productRequest);
            //return ResponseEntity.ok(productResponse);

        } catch (Exception e) {
            LOGGER.error("Failed to update Product name {}", productRequest.getProductName());
            throw new EcommerceException("Failed to update productId");
        }
          return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("{/id}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable Long id) throws EcommerceException {
        try {
            ProductResponse productResponse = productService.findProductById(id);
        } catch (Exception e) {
            LOGGER.error("Failed to find productId " + id);
            throw new EcommerceException("Failed to find productId" + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProductById(@PathVariable Long id) {
        try {
            ProductResponse productResponse = productService.findProductById(id);
              if (productResponse != null) {
                  boolean res = this.productService.deleteProductById(id);
                  return ResponseEntity.ok(res);
              }
        } catch (Exception e) {
            LOGGER.error("Not Deleted Successfully Product {} ", id);
            throw new EcommerceException("Failed to delete ProductId {}" + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

