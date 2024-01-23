package com.acro.lab.ecommerce.response;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private double price;
    private Long categoryId;
    private String categoryName;
    private Integer quantity;

}
