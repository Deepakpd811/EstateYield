package com.estateyield.repository;

import com.estateyield.dto.ExpenseDto;
import com.estateyield.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    List<Expense> findByPropertyId(Long id);
}
