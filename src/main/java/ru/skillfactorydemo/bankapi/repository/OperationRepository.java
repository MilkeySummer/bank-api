package ru.skillfactorydemo.bankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skillfactorydemo.bankapi.entity.Operation;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    // Добавили nativeQuery = true и CAST(... AS timestamp), чтобы PostgreSQL не путался с null
    @Query(value = "SELECT * FROM operations o WHERE o.account_id = :accountId " +
            "AND (CAST(:startDate AS timestamp) IS NULL OR o.created_at >= CAST(:startDate AS timestamp)) " +
            "AND (CAST(:endDate AS timestamp) IS NULL OR o.created_at <= CAST(:endDate AS timestamp)) " +
            "ORDER BY o.created_at DESC", nativeQuery = true)
    List<Operation> findOperationsByAccountIdAndDateRange(
            @Param("accountId") Long accountId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}