package com.personalfinance.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record UserRequestDTO(@NotBlank @Size(max=80) String firstName,
 @NotBlank @Size(max=80) String lastName, @NotBlank @Email @Size(max=254) String email,
 @Size(max=20) String phoneNumber, @NotNull @DecimalMin(value="0.0", inclusive=false) BigDecimal monthlyBudget) {}
