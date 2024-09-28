package com.makeupstore.services.products;

import com.makeupstore.models.ProductEntity;

import java.util.List;

public interface IProductService {
    List<ProductEntity> getAllProducts();
    ProductEntity getProductById(Long id);
    List<ProductEntity> getProductsByName(String name);
    List<ProductEntity> getProductsByBrand(String brand);
    List<ProductEntity> getProductsByCategoryName(String category);
    List<ProductEntity> getProductsByCategoryAndBrand(String category, String brand);
    ProductEntity addProduct(ProductEntity newProduct);
    ProductEntity updateProduct(ProductEntity existingProduct);
    void deleteProduct(Long id);
}
