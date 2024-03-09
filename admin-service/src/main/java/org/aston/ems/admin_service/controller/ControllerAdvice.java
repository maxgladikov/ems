package org.aston.ems.admin_service.controller;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.aston.ems.common_lib.exception.exception.BadRequestException;
import org.aston.ems.common_lib.exception.exception.NotFoundException;
import org.aston.ems.common_lib.exception.web.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException exception) {
        log.error(exception.getMessage());
        log.trace(exception.getMessage(), exception);
        return ResponseEntity.status(NOT_FOUND)
            .body(new ApiError(exception.getMessage()));
    }

    @ExceptionHandler(value = {BadRequestException.class, ConstraintViolationException.class})
    public ResponseEntity<ApiError> handleBadRequestException(RuntimeException  exception ) {
        log.error(exception.getMessage());
        log.trace(exception.getMessage(), exception);
        return ResponseEntity.badRequest()
            .body(new ApiError(exception.getMessage()));
    }

}
