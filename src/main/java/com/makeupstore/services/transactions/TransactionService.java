package com.makeupstore.services.transactions;

import com.makeupstore.dtos.transactionitemdtos.CreateTransactionItemDto;
import com.makeupstore.dtos.transactionitemdtos.GetTransactionItemDto;
import com.makeupstore.dtos.transactionitemdtos.UpdateTransactionItemDto;
import com.makeupstore.exceptions.ResourceNotFoundException;
import com.makeupstore.models.TransactionEntity;
import com.makeupstore.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<TransactionEntity> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public TransactionEntity getTransactionById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
    }

    @Override
    public Set<GetTransactionItemDto> getItemsByTransactionId(Long id) {
        return getTransactionById(id).getTransactionItems().stream().map(t -> {
            GetTransactionItemDto dto = new GetTransactionItemDto();
            dto.setId(t.getId());
            dto.setQuantity(t.getQuantity());
            dto.setTotalPrice(t.setTotalPrice());
            dto.setTransactionId(t.getTransaction().getId());
            dto.setProduct(t.getProduct());

            return dto;
        }).collect(Collectors.toSet());
    }

    @Override
    public GetTransactionItemDto addTransactionItem(CreateTransactionItemDto newItem) {
        return null;
    }

    @Override
    public GetTransactionItemDto updateTransactionItem(UpdateTransactionItemDto itemDto, Long itemId) {
        return null;
    }

    @Override
    public void deleteTransactionItem(Long itemId) {

    }


    @Override
    public TransactionEntity addTransaction() {
        return transactionRepository.save(new TransactionEntity());
    }

    @Override
    public void deleteTransaction(Long transactionId) {

    }
}
