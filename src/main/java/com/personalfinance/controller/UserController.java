package com.personalfinance.controller;
import com.personalfinance.dto.*;
import com.personalfinance.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/users") @RequiredArgsConstructor
public class UserController {
 private final UserService service;
 @PostMapping @Operation(summary="Create a user")
 public ResponseEntity<ApiResponse<UserResponseDTO>> create(@Valid @RequestBody UserRequestDTO request) {
  return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("User created successfully",service.create(request)));
 }
 @PutMapping("/{id}") @Operation(summary="Update a user")
 public ApiResponse<UserResponseDTO> update(@PathVariable Long id,@Valid @RequestBody UserRequestDTO request) {
  return new ApiResponse<>("User updated successfully",service.update(id,request));
 }
 @DeleteMapping("/{id}") @Operation(summary="Delete a user")
 public ResponseEntity<Void> delete(@PathVariable Long id) { service.delete(id); return ResponseEntity.noContent().build(); }
 @GetMapping("/{id}") @Operation(summary="Get user by ID")
 public ApiResponse<UserResponseDTO> get(@PathVariable Long id) { return new ApiResponse<>("User retrieved successfully",service.getById(id)); }
 @GetMapping @Operation(summary="Get users with pagination and sorting")
 public ApiResponse<Page<UserResponseDTO>> getAll(@RequestParam(defaultValue="0") int page,
  @RequestParam(defaultValue="20") int size,@RequestParam(defaultValue="id,desc") String[] sort) {
  return new ApiResponse<>("Users retrieved successfully",service.getAll(PageRequest.of(page,size,Sort.by(parseSort(sort)))));
 }
 private Sort.Order[] parseSort(String[] sort) {
  return java.util.Arrays.stream(sort).map(s->{String[] p=s.split(","); return new Sort.Order(p.length>1&&p[1].equalsIgnoreCase("desc")?Sort.Direction.DESC:Sort.Direction.ASC,p[0]);}).toArray(Sort.Order[]::new);
 }
}
