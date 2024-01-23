package com.acro.lab.ecommerce.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class CategoryRequest {
    private Long id;
    private String name;
    private String description;
    private LocalDate createdAt;
    private boolean isActive;

}
