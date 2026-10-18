package ru.skillfactorydemo.bankapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class OperationDto {
    private String date;
    private String type;
    private BigDecimal amount;
}
