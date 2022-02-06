package com.coding.assignment.bankservice.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UsernameAlreadyExistException extends Exception {
    public String username;
}
