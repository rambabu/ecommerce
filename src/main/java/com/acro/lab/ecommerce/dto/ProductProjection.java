package com.acro.lab.ecommerce.dto;


// Projections are used for native queries
public interface ProductProjection {
    Long getId();
    String getName();

    String getDescription();

    double getPrice();
    
    String getCategoryName();
    Integer getQuantity();
}
