package com.example.ProjectPulse.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionsHandler {
    @ExceptionHandler({
            EmployeeAlreadyExistsException.class,
            ProjectAlreadyExistsException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleConflictExceptions(Exception ex) {
        return switch (ex) {
            case EmployeeAlreadyExistsException e -> new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
            case ProjectAlreadyExistsException e  -> new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
            default                               -> new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
        };
    }

    @ExceptionHandler({
            EmployeeNotFoundException.class,
            ProjectNotFoundException.class,
            TaskNotFoundException.class,
            InvalidUsernameOrPasswordException.class,
            EmployeeNotInProjectException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundExceptions(Exception ex) {
        return switch (ex) {
            case EmployeeNotFoundException e           -> new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
            case ProjectNotFoundException e            -> new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
            case TaskNotFoundException e               -> new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
            case InvalidUsernameOrPasswordException e  -> new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
            case EmployeeNotInProjectException e       -> new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
            default                                    -> new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        };
    }


}
