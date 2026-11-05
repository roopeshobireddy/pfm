package com.personalfinance.controller;
import com.personalfinance.dto.*;
import com.personalfinance.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/expenses") @RequiredArgsConstructor
public class ExpenseController {
 private final ExpenseService service;
 @PostMapping @Operation(summary="Create an expense")
 public ResponseEntity<ApiResponse<ExpenseResponseDTO>> create(@Valid @RequestBody ExpenseRequestDTO request) {
  return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Expense created successfully",service.create(request)));
 }
 @PutMapping("/{id}") @Operation(summary="Update an expense")
 public ApiResponse<ExpenseResponseDTO> update(@PathVariable Long id,@Valid @RequestBody ExpenseRequestDTO request) {
  return new ApiResponse<>("Expense updated successfully",service.update(id,request));
 }
 @DeleteMapping("/{id}") @Operation(summary="Delete an expense")
 public ResponseEntity<Void> delete(@PathVariable Long id) { service.delete(id); return ResponseEntity.noContent().build(); }
 @GetMapping("/{id}") @Operation(summary="Get expense by ID")
 public ApiResponse<ExpenseResponseDTO> get(@PathVariable Long id) { return new ApiResponse<>("Expense retrieved successfully",service.getById(id)); }
 @GetMapping @Operation(summary="Get expenses with pagination and sorting")
 public ApiResponse<Page<ExpenseResponseDTO>> getAll(@RequestParam(defaultValue="0") int page,
  @RequestParam(defaultValue="20") int size,@RequestParam(defaultValue="expenseDate,desc") String[] sort) {
  Sort orders=Sort.by(java.util.Arrays.stream(sort).map(s->{String[] p=s.split(","); return new Sort.Order(p.length>1&&p[1].equalsIgnoreCase("desc")?Sort.Direction.DESC:Sort.Direction.ASC,p[0]);}).toList());
  return new ApiResponse<>("Expenses retrieved successfully",service.getAll(PageRequest.of(page,size,orders)));
 }
 @GetMapping("/user/{userId}") @Operation(summary="Get all expenses for a user")
 public ApiResponse<List<ExpenseResponseDTO>> getByUser(@PathVariable Long userId) {
  return new ApiResponse<>("Expenses retrieved successfully",service.getByUser(userId));
 }
}
