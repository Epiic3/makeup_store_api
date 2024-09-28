package com.makeupstore.services.products;

import com.makeupstore.exceptions.AlreadyExistsException;
import com.makeupstore.exceptions.ResourceNotFoundException;
import com.makeupstore.models.CategoryEntity;
import com.makeupstore.models.ProductEntity;
import com.makeupstore.repositories.CategoryRepository;
import com.makeupstore.repositories.ProductRepository;
import com.makeupstore.services.categories.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public List<ProductEntity> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<ProductEntity> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<ProductEntity> getProductsByCategoryName(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<ProductEntity> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategorynameAndBrand(category, brand);
    }

    @Override
    public ProductEntity addProduct(ProductEntity newProduct) {
        CategoryEntity category = Optional.ofNullable(categoryRepository.findByName(newProduct.getCategory().getName()))
                .orElseGet(() -> {
                    CategoryEntity newCategory = new CategoryEntity(newProduct.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });

        //Create and save the product with the new category

        return productRepository.save(newProduct);
    }

    @Override
    public ProductEntity updateProduct(ProductEntity existingProduct) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
