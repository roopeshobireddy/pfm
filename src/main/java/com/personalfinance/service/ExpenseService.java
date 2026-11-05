package com.personalfinance.service;
import com.personalfinance.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface ExpenseService {
 ExpenseResponseDTO create(ExpenseRequestDTO request);
 ExpenseResponseDTO update(Long id, ExpenseRequestDTO request);
 void delete(Long id);
 ExpenseResponseDTO getById(Long id);
 Page<ExpenseResponseDTO> getAll(Pageable pageable);
 List<ExpenseResponseDTO> getByUser(Long userId);
}
