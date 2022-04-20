package com.mb.ecb.parser;

import com.beust.jcommander.IStringConverter;

import java.time.LocalDate;

public class LocalDateConverter implements IStringConverter<LocalDate> {
    @Override
    public LocalDate convert(String s) {
        return LocalDate.parse(s);
    }
}
