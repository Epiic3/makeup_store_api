package com.makeupstore.services.transactions;

import com.makeupstore.dtos.transactiondtos.CreateTransactionDto;
import com.makeupstore.dtos.transactiondtos.GetTransactionAndItemsDto;
import com.makeupstore.dtos.transactiondtos.GetTransactionDto;
import com.makeupstore.dtos.transactionitemdtos.CreateTransactionItemDto;
import com.makeupstore.dtos.transactionitemdtos.GetTransactionItemDto;
import com.makeupstore.dtos.transactionitemdtos.UpdateTransactionItemDto;
import com.makeupstore.exceptions.ResourceNotFoundException;
import com.makeupstore.models.ProductEntity;
import com.makeupstore.models.TransactionEntity;
import com.makeupstore.models.TransactionItemEntity;
import com.makeupstore.repositories.TransactionRepository;
import com.makeupstore.services.products.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;
    private final ProductService productService;

    @Override
    public List<GetTransactionDto> getAllTransactions() {
        return transactionRepository.findAllTransactions();
    }

    @Override
    public GetTransactionAndItemsDto getTransactionById(Long id) {
        TransactionEntity transaction = transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

        Set<GetTransactionItemDto> items = transaction.getTransactionItems().stream()
                .map(i -> new GetTransactionItemDto(i.getId(), i.getQuantity(), i.getTotalPrice(), i.getTransaction().getId(), i.getProduct()))
                .collect(Collectors.toSet());

        return new GetTransactionAndItemsDto(transaction.getId(), transaction.getCreatedAt(), transaction.getTotalAmount(), items);
    }

    @Override
    public Set<GetTransactionItemDto> getItemsByTransactionId(Long id) {
        return transactionRepository.findItemsByTransactionId(id);
    }

    @Override
    public GetTransactionAndItemsDto addTransaction(CreateTransactionDto transactionDto) {
        //Creacion de la nueva transaccion
        TransactionEntity newTransaction = new TransactionEntity();
        newTransaction.setCreatedAt(new Date()); //Settearle la fecha

        //Crear el set de los items que se guardaran en la base de datos
        Set<TransactionItemEntity> itemsToSave = new HashSet<>();

        //llenar el set itemsToSave
        for(CreateTransactionItemDto itemDto : transactionDto.getItems()) {
            ProductEntity product = productService.getProductById(itemDto.getProductId());

            TransactionItemEntity item = new TransactionItemEntity();
            item.setQuantity(itemDto.getQuantity());
            item.setProduct(product);
            item.setTotalPriceM();
            item.setTransaction(newTransaction);
            itemsToSave.add(item);
        }

        newTransaction.setTransactionItems(itemsToSave); //Agregarle los items a la transaccion
        newTransaction.setTotalAmount(transactionDto.setTotalAmountM(itemsToSave)); //Settear el precio total de la transaccion

        //Guardar la transaccion en el repositorio
        transactionRepository.save(newTransaction);

        //Convertirlo para devolverlo
        Set<GetTransactionItemDto> items = newTransaction.getTransactionItems().stream()
                .map(i -> new GetTransactionItemDto(i.getId(), i.getQuantity(), i.getTotalPrice(), i.getTransaction().getId(), i.getProduct()))
                .collect(Collectors.toSet());

        return new GetTransactionAndItemsDto(newTransaction.getId(), newTransaction.getCreatedAt(), newTransaction.getTotalAmount(), items);
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        transactionRepository.findById(transactionId).ifPresentOrElse(transactionRepository::delete, () -> {
            throw new ResourceNotFoundException("Transaction not found");
        });
    }
}
