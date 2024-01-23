package com.acro.lab.ecommerce.dto;

public interface ProductProjection {
    Long getId();
    String getName();

    String getDescription();

    double getPrice();
    
    String getCategoryName();
    Integer getQuantity();
}
