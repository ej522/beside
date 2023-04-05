package com.example.beside.common.Exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 전역 Exception Handler

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Validation failed", errors);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(PasswordException.class)
    public ResponseEntity<?> handlePsswordException(PasswordException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Password Validation failed", errors);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(UserNotExistException.class)
    public ResponseEntity<?> handleUserNotExistException(UserNotExistException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "User Not Exist", errors);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Runtime Error", errors);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
