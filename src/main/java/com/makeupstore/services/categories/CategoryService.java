package com.makeupstore.services.categories;

import com.makeupstore.exceptions.AlreadyExistsException;
import com.makeupstore.exceptions.ResourceNotFoundException;
import com.makeupstore.models.CategoryEntity;
import com.makeupstore.repositories.CategoryRepository;
import com.makeupstore.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryEntity getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public CategoryEntity getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public CategoryEntity addCategory(CategoryEntity newCategory) {
        if(categoryRepository.existsByName(newCategory.getName()))
            throw new AlreadyExistsException("Category " + newCategory.getName() + " already exists");

        else return categoryRepository.save(newCategory);
    }

    @Override
    public CategoryEntity updateCategory(CategoryEntity newCategory, Long id) {
        CategoryEntity existingCategory = getCategoryById(id);

        existingCategory.setName(newCategory.getName());
        return categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id).ifPresentOrElse(categoryRepository::delete, () -> {
            throw new ResourceNotFoundException("Category not found");
        });
    }
}
