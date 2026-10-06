package ru.skillfactorydemo.bankapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ApiResponse {
    private BigDecimal value;
    private String message;
}