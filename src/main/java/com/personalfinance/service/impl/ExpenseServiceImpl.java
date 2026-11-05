package com.personalfinance.service.impl;
import com.personalfinance.dto.*;
import com.personalfinance.entity.*;
import com.personalfinance.exception.ResourceNotFoundException;
import com.personalfinance.mapper.ExpenseMapper;
import com.personalfinance.repository.*;
import com.personalfinance.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service @RequiredArgsConstructor
@Transactional
public class ExpenseServiceImpl implements ExpenseService {
 private final ExpenseRepository expenseRepository; private final UserRepository userRepository; private final ExpenseMapper mapper;
 @Override public ExpenseResponseDTO create(ExpenseRequestDTO request) {
  User user=userRepository.findById(request.userId()).orElseThrow(()->new ResourceNotFoundException("User not found: "+request.userId()));
  Expense expense=Expense.builder().user(user).title(request.title()).description(request.description())
   .category(request.category()).amount(request.amount()).expenseDate(request.expenseDate()).build();
  return mapper.toResponse(expenseRepository.save(expense));
 }
 @Override public ExpenseResponseDTO update(Long id, ExpenseRequestDTO request) {
  Expense expense=expenseRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Expense not found: "+id));
  User user=userRepository.findById(request.userId()).orElseThrow(()->new ResourceNotFoundException("User not found: "+request.userId()));
  mapper.updateEntity(request, expense); expense.setUser(user);
  return mapper.toResponse(expenseRepository.save(expense));
 }
 @Override public void delete(Long id) { if(!expenseRepository.existsById(id)) throw new ResourceNotFoundException("Expense not found: "+id); expenseRepository.deleteById(id); }
 @Override @Transactional(readOnly=true) public ExpenseResponseDTO getById(Long id) {
  return expenseRepository.findById(id).map(mapper::toResponse).orElseThrow(()->new ResourceNotFoundException("Expense not found: "+id));
 }
 @Override @Transactional(readOnly=true) public Page<ExpenseResponseDTO> getAll(Pageable pageable) { return expenseRepository.findAll(pageable).map(mapper::toResponse); }
 @Override @Transactional(readOnly=true) public List<ExpenseResponseDTO> getByUser(Long userId) {
  if(!userRepository.existsById(userId)) throw new ResourceNotFoundException("User not found: "+userId);
  return expenseRepository.findByUser_Id(userId).stream().map(mapper::toResponse).toList();
 }
}
