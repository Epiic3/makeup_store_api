package com.makeupstore.services.transactions;

import com.makeupstore.dtos.transactiondtos.CreateTransactionDto;
import com.makeupstore.dtos.transactiondtos.GetTransactionAndItemsDto;
import com.makeupstore.dtos.transactiondtos.GetTransactionDto;
import com.makeupstore.dtos.transactionitemdtos.CreateTransactionItemDto;
import com.makeupstore.dtos.transactionitemdtos.GetTransactionItemDto;
import com.makeupstore.dtos.transactionitemdtos.UpdateTransactionItemDto;
import com.makeupstore.models.TransactionEntity;
import com.makeupstore.models.UserEntity;

import java.util.List;
import java.util.Set;

public interface ITransactionService {
    List<GetTransactionDto> getAllTransactions();
    GetTransactionAndItemsDto getTransactionById(Long id);
    Set<GetTransactionItemDto> getItemsByTransactionId(Long id);
    GetTransactionAndItemsDto addTransaction(CreateTransactionDto transactionDto);
    void deleteTransaction(Long transactionId);
}
