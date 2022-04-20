package com.mb.ecb.parser;

import com.beust.jcommander.IStringConverter;

import java.math.BigDecimal;

public class BigDecimalConverter implements IStringConverter<BigDecimal> {
    @Override
    public BigDecimal convert(final String s) {
        return new BigDecimal(s);
    }
}
