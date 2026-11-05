package com.personalfinance.service.impl;
import com.personalfinance.dto.*;
import com.personalfinance.entity.User;
import com.personalfinance.exception.*;
import com.personalfinance.mapper.UserMapper;
import com.personalfinance.repository.UserRepository;
import com.personalfinance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service @RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
 private final UserRepository repository; private final UserMapper mapper;
 @Override public UserResponseDTO create(UserRequestDTO request) {
  if(repository.existsByEmail(request.email())) throw new DuplicateResourceException("Email already registered: "+request.email());
  return mapper.toResponse(repository.save(mapper.toEntity(request)));
 }
 @Override public UserResponseDTO update(Long id, UserRequestDTO request) {
  User user = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found: "+id));
  repository.findByEmail(request.email()).filter(existing->!existing.getId().equals(id))
   .ifPresent(existing->{throw new DuplicateResourceException("Email already registered: "+request.email());});
  user.setFirstName(request.firstName()); user.setLastName(request.lastName()); user.setEmail(request.email());
  user.setPhoneNumber(request.phoneNumber()); user.setMonthlyBudget(request.monthlyBudget());
  return mapper.toResponse(repository.save(user));
 }
 @Override public void delete(Long id) { if(!repository.existsById(id)) throw new ResourceNotFoundException("User not found: "+id); repository.deleteById(id); }
 @Override @Transactional(readOnly=true) public UserResponseDTO getById(Long id) {
  return repository.findById(id).map(mapper::toResponse).orElseThrow(()->new ResourceNotFoundException("User not found: "+id));
 }
 @Override @Transactional(readOnly=true) public Page<UserResponseDTO> getAll(Pageable pageable) { return repository.findAll(pageable).map(mapper::toResponse); }
}
