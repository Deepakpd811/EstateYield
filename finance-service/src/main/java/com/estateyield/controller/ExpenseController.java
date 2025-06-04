package com.estateyield.controller;

import com.estateyield.dto.ExpenseDto;
import com.estateyield.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // Create expense
    @PostMapping
    public ResponseEntity<ExpenseDto> createExpense(@RequestBody ExpenseDto dto) {
        ExpenseDto created = expenseService.createExpense(dto);
        return ResponseEntity.ok(created);
    }

    // Get all expenses for a property
    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<ExpenseDto>> getExpensesByProperty(@PathVariable Long propertyId) {
        return ResponseEntity.ok(expenseService.getExpensesByProperty(propertyId));
    }

    // Get one expense
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDto> getExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    // Update expense
    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDto> updateExpense(@PathVariable Long id, @RequestBody ExpenseDto dto) {
        return ResponseEntity.ok(expenseService.updateExpense(id, dto));
    }

    // Delete expense
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}
