package com.makeupstore.dtos.transactiondtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class GetTransactionDto {
    private Long id;
    private Date createdAt;
    private BigDecimal totalAmount;
    private int totalItems;
}
