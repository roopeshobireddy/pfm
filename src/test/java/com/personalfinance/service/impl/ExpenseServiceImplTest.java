package com.personalfinance.service.impl;
import com.personalfinance.dto.*;
import com.personalfinance.entity.*;
import com.personalfinance.enums.ExpenseCategory;
import com.personalfinance.exception.ResourceNotFoundException;
import com.personalfinance.mapper.ExpenseMapper;
import com.personalfinance.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class ExpenseServiceImplTest {
 @Mock ExpenseRepository expenses; @Mock UserRepository users; @Mock ExpenseMapper mapper; @InjectMocks ExpenseServiceImpl service;
 @Test void createExpenseSuccessfully() {
  User user=User.builder().id(1L).build();
  var req=new ExpenseRequestDTO(1L,"Dinner","Restaurant",ExpenseCategory.FOOD,new BigDecimal("1200"),LocalDate.parse("2025-01-15"));
  Expense saved=Expense.builder().id(7L).user(user).build();
  var response=new ExpenseResponseDTO(7L,1L,"Dinner","Restaurant",ExpenseCategory.FOOD,req.amount(),req.expenseDate(),null,null);
  when(users.findById(1L)).thenReturn(Optional.of(user)); when(expenses.save(any(Expense.class))).thenReturn(saved);
  when(mapper.toResponse(saved)).thenReturn(response);
  assertEquals(7L,service.create(req).id());
 }
 @Test void createFailsWhenUserMissing() {
  var req=new ExpenseRequestDTO(99L,"Dinner",null,ExpenseCategory.FOOD,BigDecimal.TEN,LocalDate.now());
  when(users.findById(99L)).thenReturn(Optional.empty());
  assertThrows(ResourceNotFoundException.class,()->service.create(req)); verify(expenses,never()).save(any());
 }
}
