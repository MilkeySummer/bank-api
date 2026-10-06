package ru.skillfactorydemo.bankapi.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.skillfactorydemo.bankapi.service.AccountService;

import java.math.BigDecimal;

//@Component
@RequiredArgsConstructor
public class TestRunner implements CommandLineRunner {

    private final AccountService accountService;

    @Override
    public void run(String... args) {
        System.out.println("=== START TEST ===");

        BigDecimal balance = accountService.getBalance(1L);
        System.out.println("Balance of user 1: " + balance);

        accountService.putMoney(1L, new BigDecimal("500.00"));
        System.out.println("After put: " + accountService.getBalance(1L));

        accountService.takeMoney(1L, new BigDecimal("200.00"));
        System.out.println("After take: " + accountService.getBalance(1L));

        System.out.println("=== END TEST ===");
    }
}