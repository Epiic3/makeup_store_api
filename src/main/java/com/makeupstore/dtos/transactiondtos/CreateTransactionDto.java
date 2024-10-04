package com.makeupstore.dtos.transactiondtos;

import com.makeupstore.dtos.transactionitemdtos.CreateTransactionItemDto;
import com.makeupstore.models.TransactionItemEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class CreateTransactionDto {
    private Set<CreateTransactionItemDto> items;

    public BigDecimal setTotalAmountM(Set<TransactionItemEntity> items) {
        BigDecimal totalAmount = BigDecimal.ZERO;

        for(TransactionItemEntity item : items) {
            totalAmount = totalAmount.add(item.getTotalPrice());
        }

        return totalAmount;
    }
}
