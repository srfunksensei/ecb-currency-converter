package com.mb.ecb.extractor;

import com.mb.ecb.dto.DateCurrency;
import com.mb.ecb.dto.xml.*;
import com.mb.ecb.exception.RateExtractionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

public class EcbRateExtractorTest {

    final RateExtractor rateExtractor = new EcbRateExtractor();

    @Test
    public void extractRate_envelopeNull() throws RateExtractionException {
        final DateCurrency eurDateCurrency = new DateCurrency("EUR", LocalDate.now());
        final Optional<CurrencyCube> result = rateExtractor.extractRate(null, eurDateCurrency);
        Assertions.assertFalse(result.isPresent(), "Expected NOT to find currency");
    }

    @Test
    public void extractRate_empty() throws DatatypeConfigurationException, RateExtractionException {
        final LocalDate now = LocalDate.now();

        final DateCurrency eurDateCurrency = new DateCurrency("EUR", now);
        final Envelope envelope = constructEnvelope(eurDateCurrency);

        final DateCurrency usdDateCurrency = new DateCurrency("USD", now);
        final Optional<CurrencyCube> result = rateExtractor.extractRate(envelope, usdDateCurrency);
        Assertions.assertFalse(result.isPresent(), "Expected NOT to find currency");
    }

    @Test
    public void extractRate_found() throws DatatypeConfigurationException, RateExtractionException {
        final LocalDate now = LocalDate.now();

        final DateCurrency eurDateCurrency = new DateCurrency("EUR", now);
        final Envelope envelope = constructEnvelope(eurDateCurrency);

        final Optional<CurrencyCube> result = rateExtractor.extractRate(envelope, eurDateCurrency);
        Assertions.assertTrue(result.isPresent(), "Expected to find currency");
    }

    private Envelope constructEnvelope(final DateCurrency dateCurrency) throws DatatypeConfigurationException {
        final XMLGregorianCalendar xmlDate = EcbRateExtractor.convertToXMLGregorianCalendar(dateCurrency.getDate());
        final Sender s = new Sender("sender");
        final CurrencyCube currencyCube = new CurrencyCube(dateCurrency.getCurrency(), BigDecimal.ONE);
        final TimeCube timeCube = new TimeCube(xmlDate, Collections.singletonList(currencyCube));
        final Cube cube = new Cube(Collections.singletonList(timeCube));
        return new Envelope("subject", s, cube);
    }
}
