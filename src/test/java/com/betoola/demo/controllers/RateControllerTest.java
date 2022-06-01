package com.betoola.demo.controllers;

import com.betoola.demo.dtos.RateDto;
import com.betoola.demo.enums.CurrencyCode;
import com.betoola.demo.services.RateService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RateController.class)
public class RateControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private RateService rateService;

    private String rateResponseGBP;
    private String rateResponseEUR;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        RateDto rateDtoGBP = objectMapper.readValue(new ClassPathResource("mocks/rate_gbp.json").getFile(), RateDto.class);
        rateResponseGBP = objectMapper.writeValueAsString(rateDtoGBP);
        given(rateService.makeCurrencyConversion(new BigDecimal("100"), CurrencyCode.GBP)).willReturn(rateDtoGBP);

        RateDto rateDtoEUR = objectMapper.readValue(new ClassPathResource("mocks/rate_eur.json").getFile(), RateDto.class);
        rateResponseEUR = objectMapper.writeValueAsString(rateDtoEUR);
        given(rateService.makeCurrencyConversion(new BigDecimal("50"), CurrencyCode.EUR)).willReturn(rateDtoEUR);

    }

    @Test
    public void testMustReturnOkForGBPConversion() throws Exception {
        mockMvc
            .perform(get("/rates/convert")
                .param("amount", "100")
                .param("currency", "GBP")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(rateResponseGBP));
    }

    @Test
    public void testMustReturnOkForEURConversion() throws Exception {
        mockMvc
            .perform(get("/rates/convert")
                .param("amount", "50")
                .param("currency", "EUR")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(rateResponseEUR));
    }

    @Test
    public void testMustReturnBadRequestException() throws Exception {
        mockMvc
            .perform(get("/rates/convert")
                .param("from", "EUR")
                .param("to", "GBP")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void testMustReturnNotFoundException() throws Exception {
        mockMvc
            .perform(get("/rate")
                .param("amount", "100")
                .param("currency", "GBP")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

}