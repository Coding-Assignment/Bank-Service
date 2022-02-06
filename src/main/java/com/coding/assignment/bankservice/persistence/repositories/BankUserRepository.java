package com.coding.assignment.bankservice.persistence.repositories;

import com.coding.assignment.bankservice.persistence.entities.BankUser;

import java.util.Optional;

public interface BankUserRepository extends BaseRepository<BankUser> {

    Optional<BankUser> findBankUserByUsername(String username);
}
