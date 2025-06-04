package com.estateyield.service;


import com.estateyield.dto.ExpenseDto;

import com.estateyield.model.Expense;
import com.estateyield.repository.ExpenseRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;


    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;

    }

    @Override
    public ExpenseDto createExpense(ExpenseDto dto) {

        // validation client from  property service

        dto.setExpenseDate(LocalDate.now());
        Expense expense = mapToEntity(dto);
//        expense.setProperty(property);
        Expense saved = expenseRepository.save(expense);

        return mapToDto(saved);
    }

    @Override
    public ExpenseDto getExpenseById(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense not found"));
        return mapToDto(expense);
    }

    // Use be in feign client
    @Override
    public List<ExpenseDto> getExpensesByProperty(Long propertyId) {
        return expenseRepository.findByPropertyId(propertyId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseDto updateExpense(Long id, ExpenseDto dto) {
        Expense existing = expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense not found"));

        existing.setAmount(dto.getAmount());
        existing.setExpenseType(dto.getExpenseType());
        existing.setDescription(dto.getDescription());

        Expense updated = expenseRepository.save(existing);
        return mapToDto(updated);
    }

    @Override
    public void deleteExpense(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new EntityNotFoundException("Expense not found");
        }
        expenseRepository.deleteById(id);
    }

    // ---------- Mappers ----------

    private Expense mapToEntity(ExpenseDto dto) {
        Expense expense = new Expense();
        expense.setPropertyId(dto.getPropertyId());
        expense.setAmount(dto.getAmount());
        expense.setExpenseType(dto.getExpenseType());
        expense.setDescription(dto.getDescription());
        expense.setExpenseDate(dto.getExpenseDate());
        return expense;
    }

    private ExpenseDto mapToDto(Expense entity) {
        return ExpenseDto.builder()
                .amount(entity.getAmount())
                .expenseType(entity.getExpenseType())
                .description(entity.getDescription())
                .expenseDate(entity.getExpenseDate())
                .propertyId(entity.getPropertyId())
                .build();
    }
}