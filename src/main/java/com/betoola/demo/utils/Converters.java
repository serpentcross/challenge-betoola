package com.betoola.demo.utils;

import com.betoola.demo.dtos.RateDto;
import com.betoola.demo.enums.CurrencyCode;
import com.betoola.demo.persistence.entities.Rate;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Converters {

    public static RateDto convertEntityToDto(Rate rate, BigDecimal MARGIN, BigDecimal amountFrom, CurrencyCode currency) {

        BigDecimal amountTo = amountFrom.multiply(rate.getPrice());

        return RateDto.builder()
            .amountIn(amountFrom)
            .currencyIn(rate.getCode())
            .result(calculateSumWithFee(currency, rate.getPrice().setScale(2, RoundingMode.FLOOR), amountTo, MARGIN)
        ).build();

    }

    public static RateDto.ResultDto calculateSumWithFee(CurrencyCode to, BigDecimal price, BigDecimal amountTo, BigDecimal MARGIN) {

        BigDecimal fee = amountTo.multiply(MARGIN).divide(new BigDecimal("100")).setScale(2, RoundingMode.FLOOR);

        return RateDto.ResultDto.builder()
            .exchangeFee(fee)
            .amountOut(amountTo.subtract(fee).setScale(2, RoundingMode.FLOOR))
            .currencyOut(to)
            .price(price)
        .build();

    }

}