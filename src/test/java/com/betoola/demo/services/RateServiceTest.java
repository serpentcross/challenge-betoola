package com.betoola.demo.services;

import com.betoola.demo.dtos.RateDto;
import com.betoola.demo.enums.CurrencyCode;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RateServiceTest {

    @Autowired
    private RateService rateService;

    @Test
    public void mustConvertPoundsToEuros() throws IOException {
        assertEquals(
            new ObjectMapper().readValue(new ClassPathResource("mocks/rate_gbp.json").getFile(), RateDto.class),
            rateService.getRate("100", CurrencyCode.GBP, CurrencyCode.EUR)
        );
    }

    @Test
    public void mustConvertEurosToPounds() throws IOException {
        assertEquals(
            new ObjectMapper().readValue(new ClassPathResource("mocks/rate_eur.json").getFile(), RateDto.class),
            rateService.getRate("50", CurrencyCode.EUR, CurrencyCode.GBP)
        );
    }

}