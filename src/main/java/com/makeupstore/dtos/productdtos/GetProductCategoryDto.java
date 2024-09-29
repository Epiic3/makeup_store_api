package com.makeupstore.dtos.productdtos;

import com.makeupstore.models.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class GetProductCategoryDto {
    private Long id;
    private String name;
}
