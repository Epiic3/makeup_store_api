package com.makeupstore.dtos.transactionitemdtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionItemDto {
    private int quantity;
    private Long productId;
}
