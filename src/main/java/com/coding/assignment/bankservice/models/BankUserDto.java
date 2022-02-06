package com.coding.assignment.bankservice.models;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankUserDto {

    private String username;

    private String accessToken;

    private String fullName;

}
