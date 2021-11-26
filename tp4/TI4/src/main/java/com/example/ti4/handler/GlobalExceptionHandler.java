package com.example.ti4.handler;

import com.example.ti4.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public String notFoundExceptionResolver(Exception exc) {
        return exc.getMessage();
    }

}

