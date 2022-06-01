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
        assertEquals(new BigDecimal("2.47"), rateDto.getResult().getExchangeFee());
        assertEquals(new BigDecimal("121.28"), rateDto.getResult().getAmountOut());
    }

    @Test
    public void mustSuccessfullyConvertEurosToPounds() {
        Rate rate = new Rate(2, CurrencyCode.EUR, new BigDecimal("0.8081"));
        RateDto rateDto = Converters.convertEntityToDto(rate, new BigDecimal(2), new BigDecimal(100), CurrencyCode.GBP);
        assertEquals(new BigDecimal("1.62"), rateDto.getResult().getExchangeFee());
        assertEquals(new BigDecimal("79.19"), rateDto.getResult().getAmountOut());
    }

    @Test
    public void mustNotGiveErrorIfWeConvertZero() {
        Rate rate = new Rate(2, CurrencyCode.EUR, new BigDecimal("0.8081"));
        RateDto rateDto = Converters.convertEntityToDto(rate, new BigDecimal(2), new BigDecimal(BigInteger.ZERO), CurrencyCode.GBP);
        assertEquals(new BigDecimal(BigInteger.ZERO), rateDto.getResult().getExchangeFee());
        assertEquals(new BigDecimal(BigInteger.ZERO), rateDto.getResult().getAmountOut());
    }

}