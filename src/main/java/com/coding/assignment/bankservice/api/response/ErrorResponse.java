package com.coding.assignment.bankservice.api.response;


import com.coding.assignment.bankservice.models.ApiError;

public class ErrorResponse extends Response<ApiError> {

    public ErrorResponse(ApiError data) {
        super(data, false);
    }
}
