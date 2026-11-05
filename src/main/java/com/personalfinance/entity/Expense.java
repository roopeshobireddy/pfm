package com.personalfinance.entity;
import com.personalfinance.enums.ExpenseCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Entity @Table(name="expenses", indexes={@Index(name="idx_expenses_user_date", columnList="user_id, expense_date")})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Expense extends BaseEntity {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="user_id", nullable=false) private User user;
 @NotBlank @Column(nullable=false, length=120) private String title;
 @Column(length=1000) private String description;
 @NotNull @Enumerated(EnumType.STRING) @Column(nullable=false, length=30) private ExpenseCategory category;
 @NotNull @DecimalMin(value="0.0", inclusive=false) @Column(nullable=false, precision=15, scale=2) private BigDecimal amount;
 @NotNull @Column(name="expense_date", nullable=false) private LocalDate expenseDate;
}
