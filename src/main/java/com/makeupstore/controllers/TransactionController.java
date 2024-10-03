package com.makeupstore.controllers;

import com.makeupstore.dtos.transactiondtos.GetTransaction;
import com.makeupstore.dtos.transactionitemdtos.GetTransactionItemDto;
import com.makeupstore.exceptions.ResourceNotFoundException;
import com.makeupstore.models.TransactionEntity;
import com.makeupstore.response.ApiResponse;
import com.makeupstore.services.transactions.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/transactions")
public class TransactionController {
    private final ITransactionService transactionService;

    @GetMapping("/all/items/{transactionId}")
    public ResponseEntity<ApiResponse> getItemsByTransactionId(@PathVariable Long transactionId) {
        try {
            Set<GetTransactionItemDto> transaction = transactionService.getItemsByTransactionId(transactionId);
            return ResponseEntity.ok(new ApiResponse("success", 200, transaction));
        } catch(ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), HttpStatus.NOT_FOUND, null));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getTransactionById(@PathVariable Long id) {
        try {
            TransactionEntity transaction = transactionService.getTransactionById(id);
            return ResponseEntity.ok(new ApiResponse("success", 200, transaction));
        } catch(ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), HttpStatus.NOT_FOUND, null));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addTransaction() {
        try {
            TransactionEntity transaction = transactionService.addTransaction();
            return ResponseEntity.ok(new ApiResponse("success", 200, transaction));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }
}
