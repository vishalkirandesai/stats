package com.n26.stats.rest.controllers;

import com.n26.stats.models.Transaction;
import com.n26.stats.rest.dtos.TransactionDTO;
import com.n26.stats.rest.dtos.StatisticsDTO;
import com.n26.stats.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.n26.stats.rest.mappers.TransactionMapper.*;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class TransactionRestController {

    private final TransactionService transactionService;

    public TransactionRestController(final TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("transactions")
    @ResponseStatus(CREATED)
    public TransactionDTO createTransaction(@Valid @RequestBody TransactionDTO transactionDTO) {
        Transaction transaction =
                transactionService.createTransaction(makeTransaction(transactionDTO));
        return makeTransactionDTO(transaction);
    }

    @GetMapping("statistics")
    public StatisticsDTO getStatistics() {
        return transactionService.getStatistics();
    }
}
