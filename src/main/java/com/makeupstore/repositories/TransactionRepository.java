package com.makeupstore.repositories;

import com.makeupstore.dtos.transactiondtos.GetTransactionDto;
import com.makeupstore.dtos.transactionitemdtos.GetTransactionItemDto;
import com.makeupstore.models.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query("SELECT new com.makeupstore.dtos.transactionitemdtos.GetTransactionItemDto(i.id, i.quantity, i.totalPrice, i.transaction.id, i.product) " +
           "FROM TransactionItemEntity i WHERE i.transaction.id = :transactionId")
    Set<GetTransactionItemDto> findItemsByTransactionId(Long transactionId);

    @Query("SELECT new com.makeupstore.dtos.transactiondtos.GetTransactionDto(t.id, t.createdAt, t.totalAmount, SIZE(t.transactionItems)) " +
            "FROM TransactionEntity t")
    List<GetTransactionDto> findAllTransactions();
}
