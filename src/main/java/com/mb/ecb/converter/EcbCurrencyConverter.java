package com.mb.ecb.converter;

import com.mb.ecb.cache.InMemoryCache;
import com.mb.ecb.dto.ConversionAmount;
import com.mb.ecb.dto.DateCurrency;
import com.mb.ecb.dto.xml.CurrencyCube;
import com.mb.ecb.dto.xml.Envelope;
import com.mb.ecb.exception.RateExtractionException;
import com.mb.ecb.exception.RateNotAvailableException;
import com.mb.ecb.exception.RateNotSupportedException;
import com.mb.ecb.extractor.EcbRateExtractor;
import com.mb.ecb.extractor.RateExtractor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;

public class EcbCurrencyConverter implements CurrencyConverter {

    public static final String ECB_DAILY_CHANGE_URL = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
    public static final String ECB_DAILY_HISTORY_URL = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist-90d.xml";

    private final String url;
    private final InMemoryCache<DateCurrency, BigDecimal> cache;

    public EcbCurrencyConverter(final String url) {
        this.url = url;
        this.cache = new InMemoryCache<>();
    }

    @Override
    public BigDecimal getRate(final DateCurrency dateCurrency) throws RateNotSupportedException, RateNotAvailableException {
        if (dateCurrency.getDate() == null || dateCurrency.getDate().isAfter(LocalDate.now())) {
            throw new RateNotAvailableException("Rate not available on date");
        }

        return getRateCached(dateCurrency);
    }

    private BigDecimal getRateCached(final DateCurrency dateCurrency) throws RateNotSupportedException, RateNotAvailableException {
        if (cache.containsKey(dateCurrency)) {
            cache.getValue(dateCurrency).orElseThrow(() -> new RateNotAvailableException("Cached value rate not available"));
        }

        try {
            final Envelope envelope = fetchEnvelope(url);
            final Optional<CurrencyCube> currencyCubeOpt = extractRate(envelope, dateCurrency);

            final BigDecimal rate = currencyCubeOpt.orElseThrow(() -> new RateNotSupportedException("Unsupported rate")).getRate();
            cache.cacheValue(dateCurrency, rate);
            return rate;
        } catch (JAXBException | MalformedURLException | RateExtractionException e) {
            throw new RateNotAvailableException(e.getLocalizedMessage(), e.getCause());
        }
    }

    private Envelope fetchEnvelope(final String urlString) throws JAXBException, MalformedURLException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(Envelope.class);
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        final URL url = new URL(urlString);
        return (Envelope) unmarshaller.unmarshal(url);
    }

    private Optional<CurrencyCube> extractRate(final Envelope envelope, final DateCurrency dateCurrency) throws RateExtractionException {
        final RateExtractor ecbRateExtractor = new EcbRateExtractor();
        return ecbRateExtractor.extractRate(envelope, dateCurrency);
    }

    @Override
    public BigDecimal convert(final ConversionAmount val) throws RateNotSupportedException, RateNotAvailableException {
        if (val == null || !val.isValid()) {
            throw new RateNotAvailableException("Conversion not possible with passed data");
        }

        final DateCurrency fromCurrency = val.getFromCurrency();
        final DateCurrency toCurrency = val.getToCurrency();
        final LocalDate today = LocalDate.now();
        if (fromCurrency.getDate().isEqual(today) || fromCurrency.getDate().isAfter(today)) {
            throw new RateNotAvailableException("Only past date conversion available");
        }

        final BigDecimal fromRate = getRateCached(fromCurrency),
                toRate = getRateCached(toCurrency);

        return val.getAmount().multiply(fromRate)
                .divide(toRate, fromRate.scale(), RoundingMode.HALF_UP);
    }
}
