package com.spaghettierol.demo2.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        HashMap<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors()
                .forEach(objectError -> {
                    String fieldName = ((FieldError) objectError).getField();
                    String errorName = objectError.getDefaultMessage();
                    errors.put(fieldName,errorName);
                });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConvenerNotFoundException.class)
    public ResponseEntity<?> convenerNotFoundException(ConvenerNotFoundException convenerNotFoundException){
        return new ResponseEntity<>(convenerNotFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ModuleException.class)
    public ResponseEntity<?> moduleNotFoundException(ModuleException moduleException){
        return new ResponseEntity<>(moduleException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }
}
