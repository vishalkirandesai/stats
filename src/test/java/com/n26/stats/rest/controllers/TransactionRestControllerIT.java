package com.n26.stats.rest.controllers;

import com.n26.stats.StatsApplicationTests;
import com.n26.stats.repositories.TransactionRepository;
import com.n26.stats.rest.dtos.TransactionDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionRestControllerIT extends StatsApplicationTests {

    private static final Double AMOUNT = 45675.45;
    private static final Long TIMESTAMP = 1534175019000L;
    private static final String TRANSACTIONS_ENDPOINT = "transactions";
    private static final String STATISTICS_ENDPOINT = "statistics";
    private static final String AMOUNT_STRING = "amount";
    private static final String TIMESTAMP_STRING = "timestamp";
    private static final String APPLICATION_JSON_UTF_8 = "application/json;charset=UTF-8";

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
    public void getTransactionsStatisticsTest() {
        given().
                port(port).
                contentType(APPLICATION_JSON_UTF_8).
        when().
                get(STATISTICS_ENDPOINT).
        then().
                statusCode(OK.value()).
                body("sum", equalTo(5));
    }
}
