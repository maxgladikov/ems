package org.aston.ems.admin_service.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.aston.ems.common_lib.exception.exception.NotFoundException;
import org.aston.ems.common_lib.exception.web.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException exception) {
        log.error(exception.getMessage());
        log.trace(exception.getMessage(), exception);
        return ResponseEntity.status(NOT_FOUND)
            .body(new ApiError(exception.getMessage()));
    }

    @ExceptionHandler(value = { ConstraintViolationException.class})
//    @ExceptionHandler//(value = {BadRequestException.class, ConstraintViolationException.class, BadCredentialsException.class})
    public ResponseEntity<ApiError> handleConstraintViolationException(ConstraintViolationException  exception ) {
        log.error(exception.getMessage());
        log.trace(exception.getMessage(), exception);
        String message = exception.getConstraintViolations().stream()
            .map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
        return ResponseEntity.badRequest()
            .body(new ApiError(message));
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException  e ) {
        log.error(e.getMessage());
        log.trace(e.getMessage(), e);
        return ResponseEntity.badRequest()
            .body(new ApiError(e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleAuthException(AuthenticationServiceException  exception ) {
        log.error(exception.getMessage());
        log.trace(exception.getMessage(), exception);
        return ResponseEntity.status(401)
            .body(new ApiError(exception.getMessage()));
    }

}
