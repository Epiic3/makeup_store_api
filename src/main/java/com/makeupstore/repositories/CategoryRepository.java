package com.makeupstore.repositories;

import com.makeupstore.models.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByName(String name);
    boolean existsByName(String name);
}
