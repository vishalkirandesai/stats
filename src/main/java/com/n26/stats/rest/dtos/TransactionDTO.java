package com.n26.stats.rest.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDTO {

    private TransactionDTO() {}

    private TransactionDTO(Double amount, Long timestamp) {
    }

    @NotNull
    private Double amount;

    @NotNull
    private Long timestamp;

    public Double getAmount() { return amount; }
    public Long getTimestamp() { return timestamp; }

    public static TransactionDTOBuilder newBuilder() { return new TransactionDTOBuilder(); }

    public static class TransactionDTOBuilder{

        private Double amount;
        private Long timestamp;

        public TransactionDTOBuilder setAmount(Double amount) {
            this.amount = amount;
            return this;
        }

        public TransactionDTOBuilder setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public TransactionDTO createTransactionDTO() {
            return new TransactionDTO(amount, timestamp);
        }
    }
}
