package com.makeupstore.services.products;

import com.makeupstore.dtos.productdtos.CreateProductDto;
import com.makeupstore.dtos.productdtos.UpdateProductDto;
import com.makeupstore.exceptions.ResourceNotFoundException;
import com.makeupstore.models.CategoryEntity;
import com.makeupstore.models.ProductEntity;
import com.makeupstore.repositories.CategoryRepository;
import com.makeupstore.repositories.ProductRepository;
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
    public List<ProductEntity> getProductsByCategory(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }

    @Override
    public List<ProductEntity> getProductsByCategoryAndBrand(String categoryName, String brand) {
        return productRepository.findByCategoryNameAndBrand(categoryName, brand);
    }

    @Override
    public ProductEntity addProduct(CreateProductDto newProduct) {
        CategoryEntity category = Optional.ofNullable(categoryRepository.findByName(newProduct.getCategory().getName())).orElseGet(() -> {
            CategoryEntity newCategory = new CategoryEntity(newProduct.getCategory().getName());
            return categoryRepository.save(newCategory);
        });

        newProduct.setCategory(category);
        return productRepository.save(createProduct(newProduct, category));
    }

    private ProductEntity createProduct(CreateProductDto productDto, CategoryEntity category) {
        return new ProductEntity(
            productDto.getName(),
            productDto.getBrand(),
            productDto.getPrice(),
            productDto.getInventory(),
            productDto.getDescription(),
            category
        );
    }

    @Override
    public ProductEntity updateProduct(UpdateProductDto productDto, Long productId) {
        ProductEntity existingProduct = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        updateExistingProduct(existingProduct, productDto);
        return productRepository.save(existingProduct);
    }

    private void updateExistingProduct(ProductEntity existingProduct, UpdateProductDto productDto) {
        existingProduct.setName(productDto.getName());
        existingProduct.setBrand(productDto.getBrand());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setInventory(productDto.getInventory());
        existingProduct.setDescription(productDto.getDescription());

        CategoryEntity category = Optional.ofNullable(categoryRepository.findByName(productDto.getCategory().getName())).orElseGet(() -> {
            CategoryEntity newCategory = new CategoryEntity(productDto.getCategory().getName());
            return categoryRepository.save(newCategory);
        });

        existingProduct.setCategory(category);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete, () -> {
            throw new ResourceNotFoundException("Product not found");
        });
    }
}












