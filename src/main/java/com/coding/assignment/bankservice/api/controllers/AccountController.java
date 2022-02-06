package com.coding.assignment.bankservice.api.controllers;

import com.coding.assignment.bankservice.api.request.ImportAccountRequest;
import com.coding.assignment.bankservice.models.AccountDto;
import com.coding.assignment.bankservice.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDto>> getAccounts() {
        return ResponseEntity.ok(accountService.getAccounts());
    }

    @PostMapping(value = "/accounts")
    public ResponseEntity<List<AccountDto>> importAccounts(@Valid @RequestBody List<@Valid ImportAccountRequest> accounts) {
        return ResponseEntity.ok(accountService.importAccounts(accounts));
    }
}
