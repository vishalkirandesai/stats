package com.n26.stats.rest.mappers;

import com.n26.stats.exceptions.MissingInformationException;
import com.n26.stats.models.Transaction;
import com.n26.stats.rest.dtos.TransactionDTO;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class TransactionMapperTest {

    private static final Double AMOUNT = 5465.34d;
    private static final Double AMOUNT_DTO = 5322.67d;
    private static final long NOW = System.currentTimeMillis();
    private static final ZoneId UTC = ZoneId.of("UTC");
    private static final Instant INSTANT = Instant.ofEpochMilli(NOW);
    private static final ZonedDateTime TIMESTAMP = ZonedDateTime.ofInstant(INSTANT, UTC);
    private static final Transaction TRANSACTION = new Transaction(AMOUNT, TIMESTAMP);

    private static final TransactionDTO TRANSACTION_DTO =
            TransactionDTO.newBuilder().
                    setAmount(AMOUNT_DTO).
                    setTimestamp(NOW).
                    createTransactionDTO();

    private static TransactionDTO transactionDTOTest;
    private static Transaction transactionTest;

    @Before
    public void setup() throws MissingInformationException {
        transactionDTOTest = TransactionMapper.makeTransactionDTO(TRANSACTION);
        transactionTest = TransactionMapper.makeTransaction(TRANSACTION_DTO);
    }

    @Test
    public void mapsAmountToTransactionDTO() {
        assertThat(transactionDTOTest.getAmount(), equalTo(AMOUNT));
    }

    @Test
    public void mapsAmountToTransaction() {
        assertThat(transactionTest.getAmount(), equalTo(AMOUNT_DTO));
    }

    @Test
    public void mapsTimestampToTransactionDTO() {
        assertThat(transactionDTOTest.getTimestamp(), equalTo(NOW));
    }

    @Test
    public void mapsTimestampToTransaction() {
        assertThat(transactionTest.getTimestamp(), equalTo(TIMESTAMP));
    }
}
