package com.acro.lab.ecommerce.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryRequest {
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String status;

}
