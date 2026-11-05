package com.personalfinance.dto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
public record UserResponseDTO(Long id, String firstName, String lastName, String email,
 String phoneNumber, BigDecimal monthlyBudget, LocalDateTime createdAt, LocalDateTime updatedAt) {}
