package ru.skillfactorydemo.bankapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.skillfactorydemo.bankapi.dto.ApiResponse;

import java.math.BigDecimal;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.ok(new ApiResponse(new BigDecimal("-1"), ex.getMessage()));
    }
    @ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<ApiResponse> handleInvalidAmount(InvalidAmountException ex) {   // ← тот же тип!
        return ResponseEntity.ok(new ApiResponse(BigDecimal.ZERO, ex.getMessage()));
    }
    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ApiResponse> handleInsufficientFunds(InsufficientFundsException ex) {
        return ResponseEntity.ok(new ApiResponse(BigDecimal.ZERO, ex.getMessage()));
    }
}