package com.makeupstore.services.products;

import com.makeupstore.dtos.productdtos.CreateProductDto;
import com.makeupstore.dtos.productdtos.GetProductCategoryDto;
import com.makeupstore.dtos.productdtos.GetProductDto;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    //Convert methods
    private GetProductCategoryDto convertToProductCategoryDto(CategoryEntity category) {
        return new GetProductCategoryDto(
            category.getId(),
            category.getName()
        );
    }

    private GetProductDto convertToProductDto(ProductEntity product, GetProductCategoryDto productCategoryDto) {
        return new GetProductDto(
            product.getId(),
            product.getName(),
            product.getBrand(),
            product.getPrice(),
            product.getInventory(),
            product.getDescription(),
            productCategoryDto
        );
    }

    @Override
    public List<GetProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(product -> {
            GetProductCategoryDto category = convertToProductCategoryDto(product.getCategory());
            return convertToProductDto(product, category);
        }).toList();
    }

    @Override
    public GetProductDto getProductById(Long id) {
        return productRepository.findById(id).map(product -> {
            GetProductCategoryDto category = convertToProductCategoryDto(product.getCategory());
            return convertToProductDto(product, category);
        }).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public List<GetProductDto> getProductsByName(String name) {
        return productRepository.findByName(name).stream().map(product -> {
            GetProductCategoryDto category = convertToProductCategoryDto(product.getCategory());
            return convertToProductDto(product, category);
        }).toList();
    }

    @Override
    public List<GetProductDto> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand).stream().map(product -> {
            GetProductCategoryDto category = convertToProductCategoryDto(product.getCategory());
            return convertToProductDto(product, category);
        }).toList();
    }

    @Override
    public List<GetProductDto> getProductsByCategory(String categoryName) {
        return productRepository.findByCategoryName(categoryName).stream().map(product -> {
            GetProductCategoryDto category = convertToProductCategoryDto(product.getCategory());
            return convertToProductDto(product, category);
        }).toList();
    }

    @Override
    public List<GetProductDto> getProductsByCategoryAndBrand(String categoryName, String brand) {
        return productRepository.findByCategoryNameAndBrand(categoryName, brand).stream().map(product -> {
            GetProductCategoryDto category = convertToProductCategoryDto(product.getCategory());
            return convertToProductDto(product, category);
        }).toList();
    }

    @Override
    public GetProductDto addProduct(CreateProductDto newProduct) {
        CategoryEntity category = Optional.ofNullable(categoryRepository.findByName(newProduct.getCategory().getName())).orElseGet(() -> {
            CategoryEntity newCategory = new CategoryEntity(newProduct.getCategory().getName());
            return categoryRepository.save(newCategory);
        });

        newProduct.setCategory(category);
        ProductEntity addedProduct = productRepository.save(createProduct(newProduct, category));

        GetProductCategoryDto convertedCategory = convertToProductCategoryDto(category);
        return convertToProductDto(addedProduct, convertedCategory);
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
    public GetProductDto updateProduct(UpdateProductDto productDto, Long productId) {
        ProductEntity existingProduct = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        updateExistingProduct(existingProduct, productDto);
        productRepository.save(existingProduct);

        GetProductCategoryDto category = convertToProductCategoryDto(existingProduct.getCategory());
        return convertToProductDto(existingProduct, category);
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












