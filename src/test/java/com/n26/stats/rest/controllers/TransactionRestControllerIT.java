package com.n26.stats.rest.controllers;

import com.n26.stats.StatsApplicationTests;
import com.n26.stats.models.Transaction;
import com.n26.stats.repositories.TransactionRepository;
import com.n26.stats.rest.dtos.TransactionDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionRestControllerIT extends StatsApplicationTests {

    private static final Double AMOUNT = 45675.45;
    private static final Long TIMESTAMP = 1534175019000L;
    private static final String TRANSACTIONS_ENDPOINT = "transactions";
    private static final String STATISTICS_ENDPOINT = "statistics";
    private static final String AMOUNT_STRING = "amount";
    private static final String COUNT_STRING = "count";
    private static final String TIMESTAMP_STRING = "timestamp";
    private static final String APPLICATION_JSON_UTF_8 = "application/json;charset=UTF-8";
    private static final ZoneId UTC = ZoneId.of("UTC");

    @Autowired
    private TransactionRepository transactionRepository;

    @LocalServerPort
    int port;

    @Before
    public void setup() {
        transactionRepository.deleteAll();
    }

    @Test
    public void createTransactionTest() {
        TransactionDTO transactionDTO = TransactionDTO.newBuilder().
                setAmount(AMOUNT).
                setTimestamp(TIMESTAMP).
                createTransactionDTO();
        given().
                port(port).
                contentType(APPLICATION_JSON_UTF_8).
                request().
                body(transactionDTO).
        when().
                post(TRANSACTIONS_ENDPOINT).
        then().
                statusCode(CREATED.value()).
                body(AMOUNT_STRING, equalTo(AMOUNT.floatValue())).
                body(TIMESTAMP_STRING, equalTo(TIMESTAMP));
    }

    @Test
    public void createTransactionWithNullValuesTest() {
        TransactionDTO transactionDTO = TransactionDTO.newBuilder().
                setAmount(null).
                setTimestamp(null).
                createTransactionDTO();
        given().
                port(port).
                contentType(APPLICATION_JSON_UTF_8).
                request().
                body(transactionDTO).
        when().
                post(TRANSACTIONS_ENDPOINT).
        then().
                statusCode(BAD_REQUEST.value());
    }

    @Test
    public void getTransactionsStatisticsTest() {
        createRandomNumberOfTransactions();
        given().
                port(port).
                contentType(APPLICATION_JSON_UTF_8).
        when().
                get(STATISTICS_ENDPOINT).
        then().
                statusCode(OK.value()).
                body(COUNT_STRING, equalTo(100));
    }

    // This is a very slow one. It literally waits for about a little more than a minute,
    // to test if the system stops considering transactions after 60 seconds for statistics.
    // @Test
    public void transactionStatisticsUpdateTest() throws InterruptedException {
        Thread.sleep(62000);
        given().
                port(port).
                contentType(APPLICATION_JSON_UTF_8).
        when().
                get(STATISTICS_ENDPOINT).
        then().
                statusCode(OK.value()).
                body(COUNT_STRING, equalTo(0));
    }

    private void createRandomNumberOfTransactions() {
        for (int i=0; i<100; i++) {
            Double random = Math.random();
            Instant instant = Instant.ofEpochMilli(System.currentTimeMillis());
            ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, UTC);
            Transaction transaction = new Transaction(random*i, zonedDateTime);
            transactionRepository.save(transaction);
        }
    }
}
