package com.betoola.demo.dtos;

import com.betoola.demo.enums.CurrencyCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RateDto {

    private CurrencyCode currencyIn;
    private BigDecimal amountIn;
    private ResultDto result;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResultDto {
        private CurrencyCode currencyOut;
        private BigDecimal amountOut;
        private BigDecimal price;
        private BigDecimal exchangeFee;
    }

}