package com.coding.assignment.bankservice.models;

import com.coding.assignment.bankservice.enums.AccountStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class AccountDto {

    private UUID id;

    private String password;

    private String accountNumber;

    private String clientId;

    private AccountStatus status;
}
