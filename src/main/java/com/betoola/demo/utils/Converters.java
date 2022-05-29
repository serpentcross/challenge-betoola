package com.betoola.demo.utils;

import com.betoola.demo.dtos.RateDto;
import com.betoola.demo.enums.CurrencyCode;
import com.betoola.demo.persistence.entities.Rate;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Converters {

    public static RateDto convertEntityToDto(Rate rate, BigDecimal MARGIN, BigDecimal amountFrom, CurrencyCode currencyTo) {

        BigDecimal amountTo = amountFrom.multiply(rate.getPrice());

        return RateDto.builder()
            .amountFrom(convertScientificDecimalOutputToNormal(amountFrom))
            .currencyCodeFrom(rate.getCode())
            .result(calculateSumWithFee(currencyTo, rate.getPrice(), amountTo, MARGIN)
        ).build();

    }

    public static RateDto.ResultDto calculateSumWithFee(CurrencyCode to, BigDecimal price, BigDecimal amountTo, BigDecimal MARGIN) {

        BigDecimal fee = amountTo.multiply(MARGIN).divide(new BigDecimal("100"));

        return RateDto.ResultDto.builder()
            .exchangeFee(convertScientificDecimalOutputToNormal(fee))
            .amountTo(convertScientificDecimalOutputToNormal(amountTo.subtract(fee)))
            .currencyCodeTo(to)
            .price(convertScientificDecimalOutputToNormal(price))
        .build();

    }

    public static String convertScientificDecimalOutputToNormal(BigDecimal decimal) {
        DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(2);
        return df.format(decimal.doubleValue());
    }

}