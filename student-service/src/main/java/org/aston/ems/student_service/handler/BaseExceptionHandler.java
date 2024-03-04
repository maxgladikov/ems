package org.aston.ems.student_service.handler;

import org.aston.ems.student_service.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseBody
@ControllerAdvice
public class BaseExceptionHandler {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public String resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
        return exception.getMessage();
    }
}
