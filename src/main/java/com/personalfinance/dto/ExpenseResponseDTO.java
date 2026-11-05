package com.personalfinance.dto;
import com.personalfinance.enums.ExpenseCategory;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
public record ExpenseResponseDTO(Long id, Long userId, String title, String description,
 ExpenseCategory category, BigDecimal amount, LocalDate expenseDate,
 LocalDateTime createdAt, LocalDateTime updatedAt) {}
