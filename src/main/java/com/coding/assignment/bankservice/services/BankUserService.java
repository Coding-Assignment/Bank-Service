package com.coding.assignment.bankservice.services;

import com.coding.assignment.bankservice.api.request.CreateBankUserRequest;
import com.coding.assignment.bankservice.exceptions.UsernameAlreadyExistException;
import com.coding.assignment.bankservice.mappers.BankUserMapper;
import com.coding.assignment.bankservice.models.BankUserDto;
import com.coding.assignment.bankservice.persistence.entities.BankUser;
import com.coding.assignment.bankservice.persistence.repositories.BankUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankUserService {

    private final BankUserRepository bankUserRepository;
    private final BankUserMapper bankUserMapper;
    private final BCryptPasswordEncoder encoder;

    public BankUserService(BankUserRepository bankUserRepository, BankUserMapper bankUserMapper, BCryptPasswordEncoder encoder) {
        this.bankUserRepository = bankUserRepository;
        this.bankUserMapper = bankUserMapper;
        this.encoder = encoder;
    }

    public BankUserDto createUser(CreateBankUserRequest bankUser) throws UsernameAlreadyExistException {

        Optional<BankUser> entity = bankUserRepository.findBankUserByUsername(bankUser.getUsername());

        if (entity.isPresent())
            throw new UsernameAlreadyExistException(bankUser.getUsername());
        BankUser user = bankUserMapper.convertToEntity(bankUser);
        user.setPassword(encoder.encode(bankUser.getPassword()));
        user.setAccessToken(encoder.encode(bankUser.getUsername() + bankUser.getPassword()));
        bankUserRepository.save(user);
        return bankUserMapper.convertToDto(user);
    }
}
