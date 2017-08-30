package com.n26.stats.rest.controllers;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.n26.stats.StatsApplicationTests;
import com.n26.stats.repositories.TransactionRepository;
import com.n26.stats.rest.dtos.TransactionDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.http.HttpStatus.CREATED;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionControllerIT extends StatsApplicationTests {

    private static final Double AMOUNT = 45675.45;
    private static final Long TIMESTAMP = 1534175019000l;
    private static final String TRANSACTIONS_ENDPOINT = "transactions";

    @Autowired
    private TransactionRepository transactionRepository;

    @LocalServerPort
    int port;

    @Test
    public void createtransactionTest() {
        TransactionDTO transactionDTO = TransactionDTO.newBuilder().
                setAmount(AMOUNT).
                setTimestamp(TIMESTAMP).
                createTransactionDTO();
        given().
                port(port).
                contentType(JSON).
                request().
                body(transactionDTO).
        when().
                post(TRANSACTIONS_ENDPOINT).
        then().
                statusCode(CREATED.value()).
                body("amount", equalTo(AMOUNT));
    }
}
