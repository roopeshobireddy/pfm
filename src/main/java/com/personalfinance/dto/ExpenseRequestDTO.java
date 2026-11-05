package com.personalfinance.dto;
import com.personalfinance.enums.ExpenseCategory;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
public record ExpenseRequestDTO(@NotNull @Positive Long userId, @NotBlank @Size(max=120) String title,
 @Size(max=1000) String description, @NotNull ExpenseCategory category,
 @NotNull @DecimalMin(value="0.0", inclusive=false) BigDecimal amount, @NotNull LocalDate expenseDate) {}
