package com.estateyield.service;

import com.estateyield.dto.ExpenseDto;

import java.util.List;

public interface ExpenseService {
    public ExpenseDto createExpense(ExpenseDto dto);
    public ExpenseDto getExpenseById(Long id);
    public List<ExpenseDto> getExpensesByProperty(Long propertyId);
    public ExpenseDto updateExpense(Long id, ExpenseDto dto);
    public void deleteExpense(Long id);
}
