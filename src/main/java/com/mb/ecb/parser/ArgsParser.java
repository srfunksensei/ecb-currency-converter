package com.mb.ecb.parser;

import com.beust.jcommander.Parameter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArgsParser {

    @Parameter
    private List<String> parameters = new ArrayList<>();

    @Parameter(names = {"--from", "--fromCurrency"}, description = "Currency to convert from")
    private String fromCurrency;

    @Parameter(names = {"--to", "--toCurrency"}, description = "Currency to convert to")
    private String toCurrency;

    @Parameter(names = {"--amount"}, description = "Amount to convert", converter = BigDecimalConverter.class)
    private BigDecimal amount;

    @Parameter(names = {"--onDate"}, description = "Date of conversion", converter = LocalDateConverter.class)
    private LocalDate onDate;

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getOnDate() {
        return onDate;
    }
}
