package com.makeupstore.dtos.productdtos;

import com.makeupstore.models.ProductEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class GetProductDto {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory = 0;
    private String description;
    private GetProductCategoryDto category;
}
