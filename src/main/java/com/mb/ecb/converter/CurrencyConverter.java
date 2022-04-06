package com.mb.ecb.converter;

import com.mb.ecb.dto.ConversionAmount;
import com.mb.ecb.dto.DateCurrency;
import com.mb.ecb.exception.RateNotAvailableException;
import com.mb.ecb.exception.RateNotSupportedException;

import java.math.BigDecimal;

public interface CurrencyConverter {

    BigDecimal getRate(final DateCurrency dateCurrency) throws RateNotSupportedException, RateNotAvailableException;

    BigDecimal convert(final ConversionAmount val) throws RateNotSupportedException, RateNotAvailableException;
}
