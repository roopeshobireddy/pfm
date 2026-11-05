package com.personalfinance.mapper;
import com.personalfinance.dto.*;
import com.personalfinance.entity.User;
import org.mapstruct.Mapper;
@Mapper(componentModel="spring")
public interface UserMapper {
 User toEntity(UserRequestDTO dto);
 UserResponseDTO toResponse(User user);
}
