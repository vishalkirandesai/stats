package com.n26.stats.rest.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionsStatistics {

    @NotNull
    private Double sum;

    @NotNull
    private Double avg;

    @NotNull
    private Double max;

    @NotNull
    private Double min;

    @NotNull
    private Long count;
}
