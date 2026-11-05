package com.personalfinance.service;
import com.personalfinance.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface UserService {
 UserResponseDTO create(UserRequestDTO request);
 UserResponseDTO update(Long id, UserRequestDTO request);
 void delete(Long id);
 UserResponseDTO getById(Long id);
 Page<UserResponseDTO> getAll(Pageable pageable);
}
