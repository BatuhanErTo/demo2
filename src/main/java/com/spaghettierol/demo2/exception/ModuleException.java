package com.spaghettierol.demo2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ModuleException extends RuntimeException {
    public ModuleException(String message) {
        super(message);
    }
}
