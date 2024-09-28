package com.makeupstore.services.categories;

import com.makeupstore.models.CategoryEntity;

import java.util.List;

public interface ICategoryService {
    List<CategoryEntity> getAllCategories();
    CategoryEntity getCategoryById(Long id);
    CategoryEntity getCategoryByName(String name);
    CategoryEntity addCategory(CategoryEntity newCategory);
    CategoryEntity updateCategory(CategoryEntity existingCategory, Long id);
    void deleteCategory(Long id);
}
