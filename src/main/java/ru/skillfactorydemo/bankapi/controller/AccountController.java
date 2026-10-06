package ru.skillfactorydemo.bankapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.skillfactorydemo.bankapi.dto.ApiResponse;
import ru.skillfactorydemo.bankapi.service.AccountService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/balance/{id}")
    public ApiResponse getBalance(@PathVariable Long id) {
        BigDecimal balance = accountService.getBalance(id);
        return new ApiResponse(balance, null);
    }

    @PostMapping("/put/{id}")
    public ApiResponse putMoney(@PathVariable Long id, @RequestParam BigDecimal amount) {
        accountService.putMoney(id, amount);
        return new ApiResponse(BigDecimal.ONE, null );
    }

    @PostMapping("/take/{id}")
    public ApiResponse takeMoney(@PathVariable Long id, @RequestParam BigDecimal amount) {
        accountService.takeMoney(id, amount);
        return new ApiResponse(BigDecimal.ONE, null );
    }

}