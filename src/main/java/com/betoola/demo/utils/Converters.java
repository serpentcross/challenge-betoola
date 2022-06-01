package com.betoola.demo.utils;

import com.betoola.demo.exceptions.ExceptionCode500;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Converters {

    public static BigDecimal calculateAmountGross(BigDecimal amountToConvert, BigDecimal conversionPrice) {

        if (amountToConvert == null) {
            throw new ExceptionCode500("amountToConvert");
        }

        if (conversionPrice == null) {
            throw new ExceptionCode500("conversionPrice");
        }

        return amountToConvert.divide(conversionPrice, 2, RoundingMode.FLOOR);

    }

    public static BigDecimal calculateExchangeFee(BigDecimal convertedAmountGross, BigDecimal MARGIN) {

        if (convertedAmountGross == null) {
            throw new ExceptionCode500("convertedAmountGross");
        }

        if (MARGIN == null) {
            throw new ExceptionCode500("margin");
        }

        return convertedAmountGross.multiply(MARGIN).divide(new BigDecimal("100"), RoundingMode.FLOOR).setScale(2, RoundingMode.FLOOR);

    }

}