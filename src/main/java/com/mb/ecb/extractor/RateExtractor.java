package com.mb.ecb.extractor;

import com.mb.ecb.dto.DateCurrency;
import com.mb.ecb.dto.xml.CurrencyCube;
import com.mb.ecb.dto.xml.Envelope;
import com.mb.ecb.exception.RateExtractionException;

import java.util.Optional;

public interface RateExtractor {

    Optional<CurrencyCube> extractRate(final Envelope envelope, final DateCurrency dateCurrency) throws RateExtractionException;
}
