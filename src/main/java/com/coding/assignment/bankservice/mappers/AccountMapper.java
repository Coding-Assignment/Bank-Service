package com.coding.assignment.bankservice.mappers;

import com.coding.assignment.bankservice.api.request.ImportAccountRequest;
import com.coding.assignment.bankservice.models.AccountDto;
import com.coding.assignment.bankservice.persistence.entities.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account convertToEntity(ImportAccountRequest accountRequest);

    AccountDto convertToDto(Account account);
}
