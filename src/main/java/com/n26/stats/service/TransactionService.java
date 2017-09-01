package com.n26.stats.service;

import com.n26.stats.models.Transaction;
import com.n26.stats.rest.dtos.StatisticsDTO;

public interface TransactionService {

    StatisticsDTO calculateTransactionsStatistics();

    Transaction createTransaction(Transaction transaction);

    void deleteTransaction(Long id);

    StatisticsDTO getStatistics();
}
