package com.makeupstore.services.products;

import com.makeupstore.dtos.productdtos.CreateProductDto;
import com.makeupstore.dtos.productdtos.UpdateProductDto;
import com.makeupstore.models.ProductEntity;

import java.util.List;

public interface IProductService {
    List<ProductEntity> getAllProducts();
    ProductEntity getProductById(Long id);
    List<ProductEntity> getProductsByName(String name);
    List<ProductEntity> getProductsByBrand(String brand);
    List<ProductEntity> getProductsByCategory(String category);
    List<ProductEntity> getProductsByCategoryAndBrand(String category, String brand);
    ProductEntity addProduct(CreateProductDto newProduct);
    ProductEntity updateProduct(UpdateProductDto productDto, Long productId);
    void deleteProduct(Long id);
}
