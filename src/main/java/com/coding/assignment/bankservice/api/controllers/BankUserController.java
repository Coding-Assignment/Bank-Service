package com.coding.assignment.bankservice.api.controllers;

import com.coding.assignment.bankservice.api.request.CreateBankUserRequest;
import com.coding.assignment.bankservice.exceptions.UsernameAlreadyExistException;
import com.coding.assignment.bankservice.models.BankUserDto;
import com.coding.assignment.bankservice.services.BankUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BankUserController {

    private final BankUserService bankUserService;

    public BankUserController(BankUserService bankUserService) {
        this.bankUserService = bankUserService;
    }

    @PostMapping("/bank-user")
    public ResponseEntity<BankUserDto> createUser(@Valid @RequestBody CreateBankUserRequest bankUser) throws UsernameAlreadyExistException {
        return ResponseEntity.status(HttpStatus.CREATED).body(bankUserService.createUser(bankUser));
    }
}
