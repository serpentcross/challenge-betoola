package com.betoola.demo.dtos;

import com.betoola.demo.enums.CurrencyCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RateDto {

    private CurrencyCode currencyCodeFrom;
    private String amountFrom;
    private ResultDto result;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResultDto {
        private CurrencyCode currencyCodeTo;
        private String amountTo;
        private String price;
        private String exchangeFee;
    }

}