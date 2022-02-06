package com.coding.assignment.bankservice.api.request;

import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
public class ImportAccountRequest {


    private String password;

    @Length(max = 20)
    private String accountNumber;

    private String clientId;

}
