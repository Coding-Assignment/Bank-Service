package com.coding.assignment.bankservice.mappers;

import com.coding.assignment.bankservice.api.request.CreateBankUserRequest;
import com.coding.assignment.bankservice.models.BankUserDto;
import com.coding.assignment.bankservice.persistence.entities.BankUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankUserMapper {

    BankUser convertToEntity(CreateBankUserRequest user);

    BankUserDto convertToDto(BankUser user);
}
