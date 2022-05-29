package com.betoola.demo.controllers;

import com.betoola.demo.constants.ErrorTexts;
import com.betoola.demo.dtos.RateDto;
import com.betoola.demo.enums.CurrencyCode;
import com.betoola.demo.exceptions.ExceptionCode400;
import com.betoola.demo.services.RateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rates")
public class RateController {

    private final RateService rateService;

    @Operation(summary = "Convert one currency amount to another.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operation was successful!", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RateDto.class)) }),
        @ApiResponse(responseCode = "400", description = ErrorTexts.ERROR_400_TEXT),
        @ApiResponse(responseCode = "404", description = ErrorTexts.ERROR_404_TEXT) })
    @GetMapping
    public RateDto getOne(@RequestParam String amount,
                          @Parameter @RequestParam CurrencyCode from,
                          @Parameter @RequestParam CurrencyCode to) {

        if (from.equals(to)) {
            throw new ExceptionCode400();
        }

        return rateService.getRate(amount, from, to);

    }

}