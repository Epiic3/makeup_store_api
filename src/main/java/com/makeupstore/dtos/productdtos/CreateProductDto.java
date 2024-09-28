package com.makeupstore.dtos.productdtos;

import com.makeupstore.models.CategoryEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductDto {
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory = 0;
    private String description;
    private CategoryEntity category;
}
