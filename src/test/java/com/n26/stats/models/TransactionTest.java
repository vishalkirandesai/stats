package com.n26.stats.models;

import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TransactionTest {

    private static final Double NULL_AMOUNT = null;
    private static final ZonedDateTime NULL_TIMESTAMP = null;
    private static final Double AMOUNT = 45674.45;
    private static final ZoneId UTC = ZoneId.of("UTC");
    private static final Instant TIME = Instant.ofEpochMilli(1504117409302l);
    private static final ZonedDateTime TIMESTAMP =
            ZonedDateTime.ofInstant(TIME, UTC);

    @Test
    public void createwithNullAmountTest() {
        Transaction transaction = new Transaction(NULL_AMOUNT, TIMESTAMP);
        assertNull(transaction.getAmount());
    }

    @Test
    public void createwithNullTimestamp() {
        Transaction transaction = new Transaction(AMOUNT, NULL_TIMESTAMP);
        assertNull(transaction.getTimestamp());
    }

    @Test
    public void createwithProperAmountTest() {
        Transaction transaction = new Transaction(AMOUNT, TIMESTAMP);
        assertNotNull(transaction.getAmount());
    }

    @Test
    public void createwithProperTimestampTest() {
        Transaction transaction = new Transaction(AMOUNT, TIMESTAMP);
        assertNotNull(transaction.getTimestamp());
    }
}
