package com.n26.stats.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Data
public class Transaction {

    private Transaction() {}

    public Transaction(Double amount, ZonedDateTime timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime timestamp;
}
