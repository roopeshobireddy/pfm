package com.personalfinance.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Entity @Table(name="users", uniqueConstraints=@UniqueConstraint(name="uk_users_email", columnNames="email"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User extends BaseEntity {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank @Column(nullable=false, length=80) private String firstName;
 @NotBlank @Column(nullable=false, length=80) private String lastName;
 @Email @NotBlank @Column(nullable=false, unique=true, length=254) private String email;
 @Column(length=20) private String phoneNumber;
 @NotNull @DecimalMin(value="0.0", inclusive=false) @Column(nullable=false, precision=15, scale=2) private BigDecimal monthlyBudget;
 @OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true)
 @Builder.Default private List<Expense> expenses = new ArrayList<>();
}
