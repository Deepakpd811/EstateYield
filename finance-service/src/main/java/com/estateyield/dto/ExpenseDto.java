package com.estateyield.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class ExpenseDto {


    private Long propertyId;

    private String expenseType;

    private BigDecimal amount;

    private String description;

    private LocalDate expenseDate;
}
