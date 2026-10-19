package ru.skillfactorydemo.bankapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skillfactorydemo.bankapi.entity.Account;
import ru.skillfactorydemo.bankapi.exception.InsufficientFundsException;
import ru.skillfactorydemo.bankapi.repository.AccountRepository;
import ru.skillfactorydemo.bankapi.repository.OperationRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private OperationRepository operationRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    void transferMoney_ThrowsException_WhenInsufficientFunds() {

        Long fromId = 1L;
        Long toId = 2L;
        BigDecimal transferAmount = new BigDecimal("1000.00");

        Account fromAccount = new Account();
        fromAccount.setId(fromId);
        fromAccount.setBalance(new BigDecimal("100.00"));

        Account toAccount = new Account();
        toAccount.setId(toId);
        toAccount.setBalance(new BigDecimal("500.00"));

        when(accountRepository.findById(fromId)).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findById(toId)).thenReturn(Optional.of(toAccount));

        assertThrows(InsufficientFundsException.class, () -> {
            accountService.transferMoney(fromId, toId, transferAmount);
        });

        verify(accountRepository, never()).save(any(Account.class));
        verify(operationRepository, never()).save(any());
    }

    @Test
    void transferMoney_SuccessfulTransfer_DoesNotThrowException() {
        Long fromId = 1L;
        Long toId = 2L;
        BigDecimal transferAmount = new BigDecimal("50.00");

        Account fromAccount = new Account();
        fromAccount.setId(fromId);
        fromAccount.setBalance(new BigDecimal("1000.00"));

        Account toAccount = new Account();
        toAccount.setId(toId);
        toAccount.setBalance(new BigDecimal("100.00"));

        when(accountRepository.findById(fromId)).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findById(toId)).thenReturn(Optional.of(toAccount));

        assertDoesNotThrow(() -> {
            accountService.transferMoney(fromId, toId, transferAmount);
        });

        verify(accountRepository, times(2)).save(any(Account.class));
        verify(operationRepository, times(2)).save(any());
    }
}