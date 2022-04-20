package com.mb.ecb;

import com.beust.jcommander.JCommander;
import com.mb.ecb.converter.CurrencyConverter;
import com.mb.ecb.converter.EcbCurrencyConverter;
import com.mb.ecb.dto.ConversionAmount;
import com.mb.ecb.parser.ArgsParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(final String[] argv) {
        try {
            final ArgsParser args = new ArgsParser();
            JCommander.newBuilder()
                    .addObject(args)
                    .build()
                    .parse(prepareArgv(argv));

            final ConversionAmount conversionAmount = new ConversionAmount(args.getFromCurrency(), args.getToCurrency(), args.getOnDate(), args.getAmount());
            final CurrencyConverter currencyConverter = new EcbCurrencyConverter(EcbCurrencyConverter.ECB_DAILY_HISTORY_URL);
            System.out.println(currencyConverter.convert(conversionAmount));
        } catch (Exception e) {
            System.out.println("Could not convert");
        }
    }

    private static String[] prepareArgv(final String[] argv) {
        final List<String> argvSplitted = new ArrayList<>();
        for (String arg : argv) {
            final String[] split = arg.split("=");
            argvSplitted.addAll(Arrays.asList(split));
        }
        return argvSplitted.toArray(new String[argvSplitted.size()]);
    }
}
