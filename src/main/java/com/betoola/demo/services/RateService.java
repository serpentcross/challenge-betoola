package com.betoola.demo.services;

import com.betoola.demo.dtos.RateDto;
import com.betoola.demo.enums.CurrencyCode;
import com.betoola.demo.exceptions.ExceptionCode400;
import com.betoola.demo.exceptions.ExceptionCode404;
import com.betoola.demo.persistence.entities.Rate;
import com.betoola.demo.persistence.repositories.RateRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static com.betoola.demo.utils.Converters.calculateAmountGross;
import static com.betoola.demo.utils.Converters.calculateExchangeFee;

@Service
@RequiredArgsConstructor
public class RateService {

    @Value("${margin.fee.amount}")
    private BigDecimal MARGIN;

    private final RateRepository rateRepository;

    public RateDto makeCurrencyConversion(BigDecimal amountToConvert, CurrencyCode currency) {

        BigDecimal validDecimalAmount;

        try {
            validDecimalAmount = amountToConvert;
        } catch (NumberFormatException ex) {
            throw new ExceptionCode400();
        }

        Optional<Rate> rateOptionalFrom = rateRepository.findByCode(currency);

        if (rateOptionalFrom.isPresent()) {

            Rate rate = rateOptionalFrom.get();

            BigDecimal price = rate.getPrice();
            BigDecimal convertedAmountGross = calculateAmountGross(validDecimalAmount, price);
            BigDecimal exchangeFee = calculateExchangeFee(convertedAmountGross, MARGIN);
            BigDecimal convertedAmountNet = convertedAmountGross.subtract(exchangeFee);

            return RateDto.builder()
                    .amountIn(amountToConvert)
                    .currencyIn(currency)
                    .result(
                        RateDto.ResultDto.builder()
                            .currencyOut(currency.equals(CurrencyCode.GBP) ? CurrencyCode.EUR : CurrencyCode.GBP)
                            .exchangeFee(exchangeFee)
                            .price(price.setScale(2, RoundingMode.FLOOR))
                            .amountOut(convertedAmountNet)
                        .build()
                    )
                    .build();

        } else {
            throw new ExceptionCode404();
        }

    }

}