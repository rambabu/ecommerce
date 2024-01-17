package com.acro.lab.ecommerce.request;

import com.acro.lab.ecommerce.entity.Category;
import com.acro.lab.ecommerce.entity.ProductInventory;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

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


}
