package com.n26.stats.models;

import com.n26.stats.exceptions.MissingInformationException;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.max;
import static java.util.Collections.min;
import static java.util.Comparator.comparing;

@Entity
@Data
public class Transaction {

    private Transaction() {}

    public Transaction(Double amount, ZonedDateTime timestamp) {
        this.amount = amount == null ? 0.0 : amount;
        this.timestamp = timestamp;
        addNewStats();
        setupTimedRemoval();
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime timestamp;

    private static Double sum = 0.0;
    private static Double avg = 0.0;
    private static Transaction max = null;
    private static Transaction min = null;
    private static Long count = 0L;
    private static Set<Transaction> newTransactions = new HashSet<>();

    public static Double getSum() {
        return sum;
    }

    public static Double getAvg() {
        return count == 0L ? 0.0d : sum / count;
    }

    public static Double getMax() {
        return max == null? 0.0d : max.getAmount();
    }

    public static Double getMin() {
        return min == null? 0.0d : min.getAmount();
    }

    public static Long getCount() {
        return count;
    }

    private void setupTimedRemoval() {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        if (max.equals(Transaction.this)) {
                            changeMax(getMax(newTransactions));
                        }
                        if (max.equals(Transaction.this)) {
                            changeMin(getMin(newTransactions));
                        }
                        newTransactions.remove(Transaction.this);
                        count -= 1;
                        sum -= amount;
                    }
                },
                60000
        );
    }

    private synchronized void changeMax(Transaction transaction) {
        max = transaction;
    }

    private synchronized void changeMin(Transaction transaction) {
            min = transaction;
    }

    private Transaction getMax(Set<Transaction> transactions) {
        return max(transactions, comparing(Transaction::getAmount));
    }

    private Transaction getMin(Set<Transaction> transactions) {
        return min(transactions, comparing(Transaction::getAmount));
    }

    private synchronized void addNewStats() {
        sum += this.amount;
        count += 1;
        if (max == null || this.amount > max.getAmount()) {
            max = this;
        }
        if (min == null || this.amount < min.getAmount()) {
            min = this;
        }
    }
}
