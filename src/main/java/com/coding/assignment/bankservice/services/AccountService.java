package com.coding.assignment.bankservice.services;

import com.coding.assignment.bankservice.api.request.ImportAccountRequest;
import com.coding.assignment.bankservice.enums.AccountStatus;
import com.coding.assignment.bankservice.mappers.AccountMapper;
import com.coding.assignment.bankservice.models.AccountDto;
import com.coding.assignment.bankservice.persistence.entities.Account;
import com.coding.assignment.bankservice.persistence.repositories.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    Logger logger = LoggerFactory.getLogger(AccountService.class);

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public List<AccountDto> importAccounts(List<ImportAccountRequest> accounts) {

        List<AccountDto> response = new ArrayList<>();
        logger.info("Saving Accounts...");
        for (ImportAccountRequest account : accounts) {
            Account entity = accountMapper.convertToEntity(account);
            entity.setStatus(AccountStatus.IDLE);
            accountRepository.save(entity);
            response.add(accountMapper.convertToDto(entity));
        }
        logger.info("Accounts saved successfully.");
        return response;
    }

    public List<AccountDto> getAccounts() {
        List<AccountDto> accounts = accountRepository.findAll()
                .stream()
                .map(accountMapper::convertToDto)
                .collect(Collectors.toList());

        return accounts;
    }
}
