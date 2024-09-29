package com.makeupstore.services.categories;

import com.makeupstore.dtos.categorydtos.GetCategoryDto;
import com.makeupstore.dtos.categorydtos.GetCategoryProductDto;
import com.makeupstore.dtos.productdtos.GetProductDto;
import com.makeupstore.exceptions.AlreadyExistsException;
import com.makeupstore.exceptions.ResourceNotFoundException;
import com.makeupstore.models.CategoryEntity;
import com.makeupstore.models.ProductEntity;
import com.makeupstore.repositories.CategoryRepository;
import com.makeupstore.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    private GetCategoryProductDto convertToCategoryProduct(ProductEntity product) {
        return new GetCategoryProductDto(
            product.getId(),
            product.getName(),
            product.getBrand(),
            product.getPrice(),
            product.getInventory(),
            product.getDescription()
        );
    }

    @Override
    public List<GetCategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(category -> {
            List<GetCategoryProductDto> products = category.getProducts().stream().map(this::convertToCategoryProduct).toList();
            return new GetCategoryDto(category.getId(), category.getName(), products);
        }).toList();
    }

    @Override
    public GetCategoryDto getCategoryById(Long id) {
        return categoryRepository.findById(id).map(category -> {
            List<GetCategoryProductDto> products = category.getProducts().stream().map(this::convertToCategoryProduct).toList();
            return new GetCategoryDto(category.getId(), category.getName(), products);
        }).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public GetCategoryDto getCategoryByName(String name) {
        CategoryEntity category = categoryRepository.findByName(name);
        List<GetCategoryProductDto> products = category.getProducts().stream().map(this::convertToCategoryProduct).toList();
        return new GetCategoryDto(category.getId(), category.getName(), products);
    }

    @Override
    public GetCategoryDto addCategory(CategoryEntity newCategory) {
        if(categoryRepository.existsByName(newCategory.getName()))
            throw new AlreadyExistsException("Category " + newCategory.getName() + " already exists");

        CategoryEntity category = categoryRepository.save(newCategory);
        return new GetCategoryDto(category.getId(), category.getName(), new ArrayList<>());
    }

    @Override
    public CategoryEntity updateCategory(CategoryEntity newCategory, Long id) {
//        CategoryEntity existingCategory = getCategoryById(id);
//
//        existingCategory.setName(newCategory.getName());
//        return categoryRepository.save(existingCategory);
        return null;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id).ifPresentOrElse(categoryRepository::delete, () -> {
            throw new ResourceNotFoundException("Category not found");
        });
    }
}
