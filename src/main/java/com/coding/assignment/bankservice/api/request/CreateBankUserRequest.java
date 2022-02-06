package com.coding.assignment.bankservice.api.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@AllArgsConstructor
public class CreateBankUserRequest {

    @NotBlank
    @Pattern(regexp = "[a-zA-Z0-9]{6}", message = "length must be 6")
    private String password;

    @NotBlank
    @Pattern(regexp = "[a-z0-9]{1,10}")
    private String username;

    private String fullName;

    private String accessToken;

}
