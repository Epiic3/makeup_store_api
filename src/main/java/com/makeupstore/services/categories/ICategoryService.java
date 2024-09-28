package com.makeupstore.services.categories;

import com.makeupstore.models.CategoryEntity;

import java.util.List;

public interface ICategoryService {
    List<CategoryEntity> getAllCategories();
    CategoryEntity getCategoryById(Long id);
    CategoryEntity addCategory(CategoryEntity newCategory);
    CategoryEntity updateCategory(CategoryEntity existingCategory);
    void deleteCategory(Long id);
}
