package com.n26.stats.rest.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatisticsDTO {

    private StatisticsDTO() {}

    private StatisticsDTO(Double sum,
                          Double avg,
                          Double max,
                          Double min,
                          Long count) {
        this.sum = sum;
        this.avg = avg;
        this.max = max;
        this.min = min;
        this.count = count;
    }

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

    public Double getSum() {
        return sum;
    }

    public Double getAvg() {
        return avg;
    }

    public Double getMax() {
        return max;
    }

    public Double getMin() {
        return min;
    }

    public Long getCount() {
        return count;
    }

    public static StatisticsDTOBuilder newBuilder() {
        return new StatisticsDTOBuilder();
    }

    public static class StatisticsDTOBuilder {

        private Double sum;
        private Double avg;
        private Double max;
        private Double min;
        private Long count;

        public StatisticsDTOBuilder setSum(Double sum) {
            this.sum = sum;
            return this;
        }

        public StatisticsDTOBuilder setAvg(Double avg) {
            this.avg = avg;
            return this;
        }

        public StatisticsDTOBuilder setMax(Double max) {
            this.max = max;
            return this;
        }

        public StatisticsDTOBuilder setMin(Double min) {
            this.min = min;
            return this;
        }

        public StatisticsDTOBuilder setCount(Long count) {
            this.count = count;
            return this;
        }

        public StatisticsDTO createStatisticsDTO() {
            return new StatisticsDTO(sum, avg, max, min, count);
        }
    }
}
