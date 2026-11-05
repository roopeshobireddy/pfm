package com.personalfinance.exception;
import com.personalfinance.dto.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import java.util.stream.Collectors;
import java.util.Map;
@RestControllerAdvice
public class GlobalExceptionHandler {
 @ExceptionHandler(ResourceNotFoundException.class)
 ResponseEntity<ApiResponse<Void>> notFound(ResourceNotFoundException ex) {
  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(ex.getMessage(), null));
 }
 @ExceptionHandler(DuplicateResourceException.class)
 ResponseEntity<ApiResponse<Void>> conflict(DuplicateResourceException ex) {
  return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(ex.getMessage(), null));
 }
 @ExceptionHandler({ValidationException.class, MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
 ResponseEntity<ApiResponse<Map<String,String>>> badRequest(Exception ex) {
  Map<String,String> errors = ex instanceof MethodArgumentNotValidException m
   ? m.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(
      e -> e.getField(), e -> e.getDefaultMessage()==null ? "Invalid value" : e.getDefaultMessage(), (a,b)->a))
   : Map.of("error", ex.getMessage()==null ? "Invalid request" : ex.getMessage());
  return ResponseEntity.badRequest().body(new ApiResponse<>("Validation failed", errors));
 }
 @ExceptionHandler(DataIntegrityViolationException.class)
 ResponseEntity<ApiResponse<Void>> dataConflict(DataIntegrityViolationException ex) {
  return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>("Request conflicts with existing data", null));
 }
 @ExceptionHandler(Exception.class)
 ResponseEntity<ApiResponse<Void>> internal(Exception ex) {
  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>("An unexpected error occurred", null));
 }
}
