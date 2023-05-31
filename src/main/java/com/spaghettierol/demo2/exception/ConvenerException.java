package com.spaghettierol.demo2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ConvenerException extends RuntimeException {
    public ConvenerException(String message) {
        super(message);
    }
}
