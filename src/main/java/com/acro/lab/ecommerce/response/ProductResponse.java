package com.acro.lab.ecommerce.response;

import lombok.Data;

@Data
public class ProductResponse {
    private Long productId;

    private Long categoryId;
    private String productName;
    private String productDescription;
    private float price;

}
