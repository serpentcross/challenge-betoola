package com.betoola.demo.controllers;

import com.betoola.demo.dtos.CurrencyCode;
import com.betoola.demo.dtos.RateDto;
import com.betoola.demo.services.RateService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class RatesController implements RatesApi {

    private final RateService rateService;

    @Override
    public ResponseEntity<RateDto> convertCurrency(@NotNull @Valid BigDecimal amount, @NotNull @Valid CurrencyCode currency) {
        return new ResponseEntity<>(rateService.makeCurrencyConversion(amount, currency), HttpStatus.OK);
    }

}