package com.makeupstore.services.products;

import com.makeupstore.dtos.productdtos.CreateProductDto;
import com.makeupstore.dtos.productdtos.GetProductDto;
import com.makeupstore.dtos.productdtos.UpdateProductDto;
import com.makeupstore.models.ProductEntity;

import java.util.List;

public interface IProductService {
    List<GetProductDto> getAllProducts();
    GetProductDto getProductById(Long id);
    List<GetProductDto> getProductsByName(String name);
    List<GetProductDto> getProductsByBrand(String brand);
    List<GetProductDto> getProductsByCategory(String category);
    List<GetProductDto> getProductsByCategoryAndBrand(String category, String brand);
    GetProductDto addProduct(CreateProductDto newProduct);
    GetProductDto updateProduct(UpdateProductDto productDto, Long productId);
    void deleteProduct(Long id);
}
