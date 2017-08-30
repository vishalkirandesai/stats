package com.n26.stats.rest.mappers;

import com.n26.stats.models.Transaction;
import com.n26.stats.rest.dtos.TransactionDTO;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionMapper {

    public static TransactionDTO makeTransactionDTO(Transaction transaction) {
        if (transaction == null) return null;
        return TransactionDTO.newBuilder()
                .setAmount(transaction.getAmount())
                .setTimestamp(getEpochFromZonedDateTime(transaction.getTimestamp()))
                .createTransactionDTO();
    }

    public static Transaction makeTransaction(TransactionDTO transactionDTO) {
        return new Transaction(transactionDTO.getAmount(),
                getZonedDateTime(transactionDTO.getTimestamp()));
    }

    public static List<TransactionDTO>
        makeTransactionDTOList(Collection<Transaction> transactions) {
        return transactions.stream()
                .map(TransactionMapper::makeTransactionDTO)
                .collect(Collectors.toList());
    }

    private static ZonedDateTime getZonedDateTime(Long date) {
        Instant instant = Instant.ofEpochMilli(date);
        return ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    private static Long getEpochFromZonedDateTime(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toInstant().toEpochMilli();
    }
}
