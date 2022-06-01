package com.betoola.demo.utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvertersTest {

    @Test
    public void mustSuccessfullyCalculateAmountGross() {
        assertEquals(new BigDecimal("485.24"), Converters.calculateAmountGross(new BigDecimal("600"), new BigDecimal("1.2365")));
    }

    @Test
    public void mustSuccessfullyCalculateExchangeFee() {
        assertEquals(new BigDecimal("9.70"), Converters.calculateExchangeFee(new BigDecimal("485.24"), new BigDecimal("2")));
    }

}