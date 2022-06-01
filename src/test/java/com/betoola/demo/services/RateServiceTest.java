package com.betoola.demo.services;

import com.betoola.demo.dtos.CurrencyCode;
import com.betoola.demo.dtos.RateDto;

import com.betoola.demo.exceptions.ExceptionCode404;
import com.betoola.demo.exceptions.ExceptionCode500;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class RateServiceTest {

    @Autowired
    private RateService rateService;

    @Test
    public void mustConvertEurosToPounds() throws IOException {
        assertEquals(
            new ObjectMapper().readValue(new ClassPathResource("mocks/rate_eur.json").getFile(), RateDto.class),
            rateService.makeCurrencyConversion(new BigDecimal("100"), CurrencyCode.EUR)
        );
    }

    @Test
    public void mustConvertPoundsToEuros() throws IOException {
        assertEquals(
            new ObjectMapper().readValue(new ClassPathResource("mocks/rate_gbp.json").getFile(), RateDto.class),
            rateService.makeCurrencyConversion(new BigDecimal("50"), CurrencyCode.GBP)
        );
    }

    @Test
    public void mustThrowAnExceptionOnEmptyAmount() {
        assertThrows(
            ExceptionCode500.class, () -> rateService.makeCurrencyConversion(null, CurrencyCode.EUR)
        );
    }

    @Test
    public void mustThrowAnExceptionOnEmptyCurrencyCode() {
        assertThrows(
            ExceptionCode404.class, () -> rateService.makeCurrencyConversion(new BigDecimal("50"), null)
        );
    }

}