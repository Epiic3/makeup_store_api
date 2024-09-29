package com.makeupstore.dtos.categorydtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetCategoryDto {
    private Long id;
    private String name;
    List<GetCategoryProductDto> products;
}
