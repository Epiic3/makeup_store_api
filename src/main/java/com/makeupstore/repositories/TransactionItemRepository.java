package com.makeupstore.repositories;

import com.makeupstore.models.TransactionItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionItemRepository extends JpaRepository<TransactionItemEntity, Long> {
}
