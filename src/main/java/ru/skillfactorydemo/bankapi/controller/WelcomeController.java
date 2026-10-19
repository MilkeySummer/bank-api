package ru.skillfactorydemo.bankapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public Map<String, Object> welcome() {
        return Map.of(
                "message", "Bank API is running!",
                "version", "1.0",
                "endpoints", Map.of(
                        "balance", "/api/account/balance/{id}",
                        "put", "POST /api/account/put/{id}?amount={amount}",
                        "take", "POST /api/account/take/{id}?amount={amount}",
                        "transfer", "POST /api/account/transfer?from={id}&to={id}&amount={amount}",
                        "operations", "/api/account/operations/{id}"
                ),
                "demo", "https://bank-api-production-8532.up.railway.app/api/account/balance/1"
        );
    }
}