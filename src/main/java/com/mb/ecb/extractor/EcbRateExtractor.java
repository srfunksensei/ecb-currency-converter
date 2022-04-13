package com.mb.ecb.extractor;

import com.mb.ecb.dto.DateCurrency;
import com.mb.ecb.dto.xml.Cube;
import com.mb.ecb.dto.xml.CurrencyCube;
import com.mb.ecb.dto.xml.Envelope;
import com.mb.ecb.dto.xml.TimeCube;
import com.mb.ecb.exception.RateExtractionException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public class EcbRateExtractor implements RateExtractor {

    @Override
    public Optional<CurrencyCube> extractRate(final Envelope envelope, final DateCurrency dateCurrency) throws RateExtractionException {
        try {
            return extractRate(Optional.ofNullable(envelope), dateCurrency);
        } catch (DatatypeConfigurationException e) {
            throw new RateExtractionException("Extraction failed: date cannot be parsed correctly", e.getCause());
        }
    }

    private Optional<CurrencyCube> extractRate(final Optional<Envelope> envelopeOpt, final DateCurrency dateCurrency) throws DatatypeConfigurationException {
        if (envelopeOpt.isPresent()) {
            final Optional<TimeCube> timeCubeOpt = extractTimeCube(dateCurrency.getDate(), Optional.ofNullable(envelopeOpt.get().getCube()));
            return extractCurrencyCube(dateCurrency.getCurrency(), timeCubeOpt);
        }

        return Optional.empty();
    }

    private Optional<TimeCube> extractTimeCube(final LocalDate date, final Optional<Cube> cubeOpt) throws DatatypeConfigurationException {
        if (date != null && cubeOpt.isPresent()) {
            final XMLGregorianCalendar xmlGregorianCalendar = convertToXMLGregorianCalendar(date);
            return collectionToStream(cubeOpt.get().getCube())
                    .filter(tc -> tc.getTime() != null && tc.getTime().equals(xmlGregorianCalendar))
                    .findFirst();
        }

        return Optional.empty();
    }

    private Optional<CurrencyCube> extractCurrencyCube(final String currency, final Optional<TimeCube> timeCubeOpt) {
        if (currency != null && timeCubeOpt.isPresent()) {
            return collectionToStream(timeCubeOpt.get().getCube())
                    .filter(cc -> currency.equalsIgnoreCase(cc.getCurrency()))
                    .findFirst();
        }

        return Optional.empty();
    }

    public static <T> Stream<T> collectionToStream(final Collection<T> collection) {
        return Optional.ofNullable(collection)
                .map(Collection::stream)
                .orElseGet(Stream::empty);
    }

    public static XMLGregorianCalendar convertToXMLGregorianCalendar(final LocalDate localDate) throws DatatypeConfigurationException {
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate.toString());
    }
}
