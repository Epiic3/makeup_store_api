package com.makeupstore.dtos.transactionitemdtos;

import com.makeupstore.models.ProductEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetTransactionItemDto {
    private Long id;
    private int quantity = 1;
    private BigDecimal totalPrice;
    private Long transactionId;
    private ProductEntity product;
}
