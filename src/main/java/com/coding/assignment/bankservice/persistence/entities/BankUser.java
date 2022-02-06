package com.coding.assignment.bankservice.persistence.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bank_user")
public class BankUser extends BaseEntity {

    private String password;

    private String username;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "access_token")
    private String accessToken;
}
