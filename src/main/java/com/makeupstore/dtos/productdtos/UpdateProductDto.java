package com.makeupstore.dtos.productdtos;

import com.makeupstore.models.CategoryEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductDto {
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private CategoryEntity category;
}
