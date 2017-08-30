package com.n26.stats.service;

import com.n26.stats.models.Transaction;
import com.n26.stats.repositories.TransactionRepository;
import com.n26.stats.rest.dtos.TransactionsStatistics;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService{

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(final TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionsStatistics calculateTransactionsStatistics() {
        return null;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.delete(id);
    }
}
