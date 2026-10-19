package ru.skillfactorydemo.bankapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import ru.skillfactorydemo.bankapi.entity.Account;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryIntegrationTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void shouldSaveAndFindAccount() {
        Account account = new Account();
        account.setBalance(new BigDecimal("1000.00"));

        Account saved = accountRepository.save(account);
        Optional<Account> found = accountRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getBalance()).isEqualByComparingTo(new BigDecimal("1000.00"));
    }

    @Test
    void shouldReturnEmptyForNonExistentAccount() {
        Optional<Account> found = accountRepository.findById(9999L);
        assertThat(found).isEmpty();
    }
}