package com.betoola.demo.utils;

import com.betoola.demo.dtos.RateDto;
import com.betoola.demo.enums.CurrencyCode;
import com.betoola.demo.persistence.entities.Rate;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvertersTest {

    @Test
    public void mustSuccessfullyConvertPoundsToEuros() {
        Rate rate = new Rate(1, CurrencyCode.GBP, new BigDecimal("1.2375"));
        RateDto rateDto = Converters.convertEntityToDto(rate, new BigDecimal(2), new BigDecimal(100), CurrencyCode.EUR);
        assertEquals("2.48", rateDto.getResult().getExchangeFee());
        assertEquals("121.28", rateDto.getResult().getAmountTo());
    }

    @Test
    public void mustSuccessfullyConvertEurosToPounds() {
        Rate rate = new Rate(2, CurrencyCode.EUR, new BigDecimal("0.8081"));
        RateDto rateDto = Converters.convertEntityToDto(rate, new BigDecimal(2), new BigDecimal(100), CurrencyCode.GBP);
        assertEquals("1.62", rateDto.getResult().getExchangeFee());
        assertEquals("79.19", rateDto.getResult().getAmountTo());
    }

    @Test
    public void mustNotGiveErrorIfWeConvertZero() {
        Rate rate = new Rate(2, CurrencyCode.EUR, new BigDecimal("0.8081"));
        RateDto rateDto = Converters.convertEntityToDto(rate, new BigDecimal(2), new BigDecimal(BigInteger.ZERO), CurrencyCode.GBP);
        assertEquals("0", rateDto.getResult().getExchangeFee());
        assertEquals("0", rateDto.getResult().getAmountTo());
    }

    @Test
    public void mustGiveCorrectZeroValue() {
        assertEquals("0", Converters.convertScientificDecimalOutputToNormal(new BigDecimal(BigInteger.ZERO)));
    }

    @Test
    public void mustGiveCorrectFormattedValue() {
        assertEquals("1.44", Converters.convertScientificDecimalOutputToNormal(new BigDecimal("1.43743849")));
    }

}