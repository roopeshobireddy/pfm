package com.personalfinance.mapper;
import com.personalfinance.dto.*;
import com.personalfinance.entity.Expense;
import org.mapstruct.*;
@Mapper(componentModel="spring")
public interface ExpenseMapper {
 @Mapping(target="userId", source="user.id")
 ExpenseResponseDTO toResponse(Expense expense);
 @Mapping(target="id", ignore=true) @Mapping(target="user", ignore=true)
 @Mapping(target="createdAt", ignore=true) @Mapping(target="updatedAt", ignore=true)
 void updateEntity(ExpenseRequestDTO dto, @MappingTarget Expense expense);
}
