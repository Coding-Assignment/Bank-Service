package com.coding.assignment.bankservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {
    private String code;
    private String message;
}
