package com.betoola.demo.persistence.entities;

import com.betoola.demo.dtos.CurrencyCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import java.math.BigDecimal;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rate {

    @Id
    private long id;

    @Enumerated(EnumType.STRING)
    private CurrencyCode code;

    private BigDecimal price;

}