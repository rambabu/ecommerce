package com.acro.lab.ecommerce.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ProductRequest {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private double price;
    @NotNull
    private Long categoryId;
    @Range(min=0, max = 1000)
    private Integer quantity;
}
