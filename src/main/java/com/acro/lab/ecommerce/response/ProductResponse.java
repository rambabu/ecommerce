package com.acro.lab.ecommerce.response;

import lombok.Data;

@Data
public class ProductResponse {
    private String name;
    private String description;
    private double price;
    private Long categoryId;

}
