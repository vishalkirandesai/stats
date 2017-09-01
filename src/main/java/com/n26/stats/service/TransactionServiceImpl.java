package com.n26.stats.service;

import com.n26.stats.models.Transaction;
import com.n26.stats.repositories.TransactionRepository;
import com.n26.stats.rest.dtos.StatisticsDTO;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.n26.stats.models.Transaction.*;
import static java.util.Collections.max;
import static java.util.Collections.min;
import static java.util.Comparator.comparing;

@Service
public class TransactionServiceImpl implements TransactionService{

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(final TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public StatisticsDTO calculateTransactionsStatistics() {
        return null;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        transaction = transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.delete(id);
    }

    @Override
    public StatisticsDTO getStatistics() {
        return StatisticsDTO.
                newBuilder().
                setSum(getSum()).
                setAvg(getAvg()).
                setMax(getMax()).
                setMin(getMin()).
                setCount(getCount()).
                createStatisticsDTO();
    }
}
