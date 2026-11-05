package com.personalfinance.service.impl;
import com.personalfinance.dto.*;
import com.personalfinance.entity.User;
import com.personalfinance.exception.DuplicateResourceException;
import com.personalfinance.mapper.UserMapper;
import com.personalfinance.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
 @Mock UserRepository repository; @Mock UserMapper mapper; @InjectMocks UserServiceImpl service;
 @Test void createUserSuccessfully() {
  var req=new UserRequestDTO("Jane","Doe","jane@example.com","9876543210",new BigDecimal("50000"));
  var entity=User.builder().id(1L).email(req.email()).build();
  var response=new UserResponseDTO(1L,"Jane","Doe",req.email(),"9876543210",req.monthlyBudget(),null,null);
  when(repository.existsByEmail(req.email())).thenReturn(false); when(mapper.toEntity(req)).thenReturn(entity);
  when(repository.save(entity)).thenReturn(entity); when(mapper.toResponse(entity)).thenReturn(response);
  assertEquals(1L,service.create(req).id()); verify(repository).save(entity);
 }
 @Test void createRejectsDuplicateEmail() {
  var req=new UserRequestDTO("Jane","Doe","jane@example.com","9876543210",new BigDecimal("50000"));
  when(repository.existsByEmail(req.email())).thenReturn(true);
  assertThrows(DuplicateResourceException.class,()->service.create(req)); verify(repository,never()).save(any());
 }
}
