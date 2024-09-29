package com.makeupstore.services.categories;

import com.makeupstore.dtos.categorydtos.GetCategoryDto;
import com.makeupstore.models.CategoryEntity;

import java.util.List;

public interface ICategoryService {
    List<GetCategoryDto> getAllCategories();
    GetCategoryDto getCategoryById(Long id);
    GetCategoryDto getCategoryByName(String name);
    GetCategoryDto addCategory(CategoryEntity newCategory);
    CategoryEntity updateCategory(CategoryEntity existingCategory, Long id);
    void deleteCategory(Long id);
}
