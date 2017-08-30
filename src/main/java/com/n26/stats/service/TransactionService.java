package com.n26.stats.service;

import com.n26.stats.models.Transaction;
import com.n26.stats.rest.dtos.TransactionsStatistics;

import java.util.List;

public interface TransactionService {

    TransactionsStatistics calculateTransactionsStatistics();

    Transaction createTransaction(Transaction transaction);

    void deleteTransaction(Long id);
}
