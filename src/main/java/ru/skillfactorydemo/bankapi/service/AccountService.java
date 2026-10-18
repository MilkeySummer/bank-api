package ru.skillfactorydemo.bankapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillfactorydemo.bankapi.entity.Account;
import ru.skillfactorydemo.bankapi.exception.InsufficientFundsException;
import ru.skillfactorydemo.bankapi.exception.InvalidAmountException;
import ru.skillfactorydemo.bankapi.exception.UserNotFoundException;
import ru.skillfactorydemo.bankapi.repository.AccountRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillfactorydemo.bankapi.repository.OperationRepository;
import ru.skillfactorydemo.bankapi.entity.OperationType;
import ru.skillfactorydemo.bankapi.entity.Operation;

import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {
    private final AccountRepository accountRepository;
    private final OperationRepository operationRepository;

    public BigDecimal getBalance(Long id) {
        return accountRepository.findById(id)
                .map(Account::getBalance)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public void putMoney(Long id, BigDecimal amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Amount must be positive");
        }

        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);

        Operation operation = new Operation(account, OperationType.PUT, amount);
        operationRepository.save(operation);
    }

    public void takeMoney(Long id, BigDecimal amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Amount must be positive");
        }

        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);

        Operation operation = new Operation(account, OperationType.TAKE, amount);
        operationRepository.save(operation);
    }

    public List<Operation> getOperationList(Long accountId, LocalDateTime startDate, LocalDateTime endDate) {
        return operationRepository.findOperationsByAccountIdAndDateRange(accountId, startDate, endDate);
    }
}
