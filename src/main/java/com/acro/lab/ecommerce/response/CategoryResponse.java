package com.acro.lab.ecommerce.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDate createdAt;
    private boolean isActive;

}
