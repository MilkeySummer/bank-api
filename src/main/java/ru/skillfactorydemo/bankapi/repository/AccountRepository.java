package ru.skillfactorydemo.bankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillfactorydemo.bankapi.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // Spring сам сгенерирует методы: save, findById, findAll, deleteById и т.д.
}