package com.betoola.demo.persistence.repositories;

import com.betoola.demo.enums.CurrencyCode;
import com.betoola.demo.persistence.entities.Rate;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RateRepository extends JpaRepository<Rate, Integer> {
    Optional<Rate> findByCode(CurrencyCode code);
}