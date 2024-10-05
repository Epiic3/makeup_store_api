package com.makeupstore.repositories;

import com.makeupstore.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByName(String name);
    List<ProductEntity> findByBrand(String brand);
    List<ProductEntity> findByCategoryName(String category);
    List<ProductEntity> findByCategoryNameAndBrand(String category, String brand);
    boolean existsByUniqueName(String uniqueName);
}
