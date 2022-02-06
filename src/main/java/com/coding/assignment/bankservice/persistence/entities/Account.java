package com.coding.assignment.bankservice.persistence.entities;

import com.coding.assignment.bankservice.enums.AccountStatus;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account extends BaseEntity {

    private String password;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "client_id")
    private String clientId;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;
}
