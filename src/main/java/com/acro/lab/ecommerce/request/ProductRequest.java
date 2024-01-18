package com.acro.lab.ecommerce.request;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class ProductRequest {
    @NotNull
    Long categoryId;

    String productName;
    String productDescription;
    @NotNull
    float price;

}
