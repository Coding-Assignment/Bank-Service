package com.coding.assignment.bankservice.exceptions.handler;

import com.coding.assignment.bankservice.api.response.ErrorResponse;
import com.coding.assignment.bankservice.enums.LocaleMessages;
import com.coding.assignment.bankservice.exceptions.UsernameAlreadyExistException;
import com.coding.assignment.bankservice.models.ApiError;
import com.coding.assignment.bankservice.services.TranslatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private final TranslatorService translator;

    public GlobalExceptionHandler(TranslatorService translator) {
        this.translator = translator;
    }

    @ExceptionHandler(value = UsernameAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> exception(UsernameAlreadyExistException exception) {
        log.debug("Username Already Exist", exception);
        var error = new ApiError("UsernameAlreadyExist",
                translator.toLocale(LocaleMessages.Username_Already_Exist.name(), exception.username));
        ErrorResponse response = new ErrorResponse(error);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handle(ConstraintViolationException exception) {
        log.debug("Constraint Violation Exception", exception);

        var error = new ApiError("ValidationFailed",exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList()).toString());
        ErrorResponse response = new ErrorResponse(error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException exception) {
        log.debug("Method Argument Not Valid", exception);

        var error = new ApiError("MethodArgumentNotValid",exception.getMessage());
        ErrorResponse response = new ErrorResponse(error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
