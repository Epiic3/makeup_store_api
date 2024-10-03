package com.makeupstore.dtos.transactiondtos;

import com.makeupstore.dtos.transactionitemdtos.GetTransactionItemDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
public class GetTransaction {
    private Long id;
    private Date createdAt;
    private BigDecimal totalAmount;
    private Set<GetTransactionItemDto> items;
}
