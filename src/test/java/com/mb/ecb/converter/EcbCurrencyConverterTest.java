package com.mb.ecb.converter;

import com.mb.ecb.dto.ConversionAmount;
import com.mb.ecb.dto.DateCurrency;
import com.mb.ecb.exception.RateNotAvailableException;
import com.mb.ecb.exception.RateNotSupportedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EcbCurrencyConverterTest {

    @Test
    public void getRate_unsupportedCurrency() {
        final CurrencyConverter ecbCurrencyConverter = new EcbCurrencyConverter(EcbCurrencyConverter.ECB_DAILY_HISTORY_URL);

        Assertions.assertThrows(RateNotSupportedException.class, () -> {
            final DateCurrency dateCurrency = new DateCurrency("RSD", LocalDate.now().minusDays(1));
            ecbCurrencyConverter.getRate(dateCurrency);
        });
    }

    @Test
    public void getRate_unavailableRateOnDate() {
        final CurrencyConverter ecbCurrencyConverter = new EcbCurrencyConverter(EcbCurrencyConverter.ECB_DAILY_HISTORY_URL);

        Assertions.assertThrows(RateNotAvailableException.class, () -> {
            final DateCurrency dateCurrency = new DateCurrency("USD", LocalDate.now().plusDays(1));
            ecbCurrencyConverter.getRate(dateCurrency);
        });

        Assertions.assertThrows(RateNotAvailableException.class, () -> {
            final DateCurrency dateCurrency = new DateCurrency("USD", null);
            ecbCurrencyConverter.getRate(dateCurrency);
        });
    }

    @Test
    public void getRate_malformedUrl() {
        final CurrencyConverter ecbCurrencyConverter = new EcbCurrencyConverter(null);

        Assertions.assertThrows(RateNotAvailableException.class, () -> {
            final DateCurrency dateCurrency = new DateCurrency("USD", LocalDate.now().minusDays(1));
            ecbCurrencyConverter.getRate(dateCurrency);
        });
    }

    @Test
    public void getRate_noEnvelope() {
        final CurrencyConverter ecbCurrencyConverter = new EcbCurrencyConverter("https://www.google.com");

        Assertions.assertThrows(RateNotAvailableException.class, () -> {
            final DateCurrency dateCurrency = new DateCurrency("USD", LocalDate.now().minusDays(1));
            ecbCurrencyConverter.getRate(dateCurrency);
        });
    }

    @Test
    public void getRate() throws RateNotSupportedException, RateNotAvailableException {
        final CurrencyConverter ecbCurrencyConverter = new EcbCurrencyConverter(EcbCurrencyConverter.ECB_DAILY_HISTORY_URL);

        final DateCurrency dateCurrency = new DateCurrency("USD", LocalDate.now().minusDays(1));
        final BigDecimal rate = ecbCurrencyConverter.getRate(dateCurrency);
        Assertions.assertNotNull(rate);
    }

    @Test
    public void convert_insufficientData() {
        final CurrencyConverter ecbCurrencyConverter = new EcbCurrencyConverter(EcbCurrencyConverter.ECB_DAILY_HISTORY_URL);

        Assertions.assertThrows(RateNotAvailableException.class, () -> ecbCurrencyConverter.convert(null));
        Assertions.assertThrows(RateNotAvailableException.class, () -> ecbCurrencyConverter.convert(new ConversionAmount("", "      ", null, null)));
    }

    @Test
    public void convert_futureDate() {
        final CurrencyConverter ecbCurrencyConverter = new EcbCurrencyConverter(EcbCurrencyConverter.ECB_DAILY_HISTORY_URL);

        Assertions.assertThrows(RateNotAvailableException.class, () -> ecbCurrencyConverter.convert(new ConversionAmount("CHF", "USD", LocalDate.now().plusMonths(1), BigDecimal.ONE)));
    }

    @Test
    public void convert() throws RateNotSupportedException, RateNotAvailableException {
        final CurrencyConverter ecbCurrencyConverter = new EcbCurrencyConverter(EcbCurrencyConverter.ECB_DAILY_HISTORY_URL);

        final BigDecimal result = ecbCurrencyConverter.convert(new ConversionAmount("CHF", "USD", LocalDate.now().minusDays(2), BigDecimal.ONE));
        Assertions.assertNotNull(result);
    }
}
