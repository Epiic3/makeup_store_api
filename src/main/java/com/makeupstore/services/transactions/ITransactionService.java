package com.makeupstore.services.transactions;

import com.makeupstore.dtos.transactionitemdtos.CreateTransactionItemDto;
import com.makeupstore.dtos.transactionitemdtos.GetTransactionItemDto;
import com.makeupstore.dtos.transactionitemdtos.UpdateTransactionItemDto;
import com.makeupstore.models.TransactionEntity;

import java.util.List;
import java.util.Set;

public interface ITransactionService {
    List<TransactionEntity> getAllTransactions();
    TransactionEntity getTransactionById(Long id);
    Set<GetTransactionItemDto> getItemsByTransactionId(Long id);
    GetTransactionItemDto addTransactionItem(CreateTransactionItemDto newItem);
    GetTransactionItemDto updateTransactionItem(UpdateTransactionItemDto itemDto, Long itemId);
    void deleteTransactionItem(Long itemId);
    TransactionEntity addTransaction();
    void deleteTransaction(Long transactionId);
}
