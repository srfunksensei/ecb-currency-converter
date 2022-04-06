package com.mb.ecb.dto;

import java.time.LocalDate;

public class DateCurrency {

    private final String currency;
    private final LocalDate date;

    public DateCurrency(final String currency, final LocalDate date) {
        this.currency = currency;
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDate getDate() {
        return date;
    }
}
