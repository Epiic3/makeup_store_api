package com.makeupstore.repositories;

import com.makeupstore.dtos.transactiondtos.GetTransaction;
import com.makeupstore.models.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
