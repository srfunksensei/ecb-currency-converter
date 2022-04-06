package com.mb.ecb.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ConversionAmount {
    private final String fromCurrency;
    private final String toCurrency;
    private final LocalDate onDate;
    private final BigDecimal amount;

    public ConversionAmount(String fromCurrency, String toCurrency, LocalDate onDate, BigDecimal amount) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.onDate = onDate;
        this.amount = amount;
    }

    public DateCurrency getFromCurrency() {
        return new DateCurrency(this.fromCurrency, this.onDate);
    }

    public DateCurrency getToCurrency() {
        return new DateCurrency(this.toCurrency, this.onDate);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public boolean isValid() {
        return fromCurrency != null && !fromCurrency.isBlank() &&
                toCurrency != null && !toCurrency.isBlank() &&
                onDate != null && amount != null;
    }
}
