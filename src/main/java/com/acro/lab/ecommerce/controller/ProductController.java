package com.acro.lab.ecommerce.controller;

import com.acro.lab.ecommerce.exceptions.ProductException;
import com.acro.lab.ecommerce.request.ProductRequest;
import com.acro.lab.ecommerce.response.ProductResponse;
import com.acro.lab.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/product")
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(@Autowired ProductService productService){
        this.productService=productService;
    }
    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest productRequest)
       {
           try{
               if(productRequest != null){
                   ProductResponse productResponse=productService.createProduct(productRequest);
                   }
           }
           catch(Exception e){
               log.error("create failed");
               throw new ProductException("creation failed",e);
           }
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
}


