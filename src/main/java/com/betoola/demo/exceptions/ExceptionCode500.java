package com.betoola.demo.exceptions;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

import static com.betoola.demo.constants.ErrorTexts.ERROR_500_TEXT;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ExceptionCode500 extends RuntimeException {

    public ExceptionCode500(final String parameterName) {
        super(ERROR_500_TEXT + parameterName);
    }

}