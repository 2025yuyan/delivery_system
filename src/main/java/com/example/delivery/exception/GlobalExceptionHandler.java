package com.example.delivery.exception;

import com.example.delivery.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public Result<?> handleException(RuntimeException ex) {
        return Result.error(ex.getMessage());
    }
}
