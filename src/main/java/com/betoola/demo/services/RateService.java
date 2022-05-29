package com.betoola.demo.services;

import com.betoola.demo.dtos.RateDto;
import com.betoola.demo.enums.CurrencyCode;
import com.betoola.demo.exceptions.ExceptionCode400;
import com.betoola.demo.exceptions.ExceptionCode404;
import com.betoola.demo.persistence.entities.Rate;
import com.betoola.demo.persistence.repositories.RateRepository;
import com.betoola.demo.utils.Converters;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RateService {

    @Value("${margin.fee.amount}")
    private BigDecimal MARGIN;

    private final RateRepository rateRepository;

    public RateDto getRate(String amount, CurrencyCode from, CurrencyCode to) {

        BigDecimal validDecimalAmount;

        try {
            validDecimalAmount = new BigDecimal(amount);
        } catch (NumberFormatException ex) {
            throw new ExceptionCode400();
        }

        Optional<Rate> rateOptionalFrom = rateRepository.findByCode(from);

        if (rateOptionalFrom.isPresent()) {
            return Converters.convertEntityToDto(rateOptionalFrom.get(), MARGIN, validDecimalAmount, to);
        } else {
            throw new ExceptionCode404();
        }

    }

}