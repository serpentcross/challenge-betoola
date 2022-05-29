package com.betoola.demo.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true)
public enum CurrencyCode {
    EUR, GBP
}