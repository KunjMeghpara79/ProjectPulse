package com.example.ProjectPulse.Exceptions;

public class EmployeeNotInProjectException extends RuntimeException{

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public EmployeeNotInProjectException(String message){
        this.message = message;
    }
}
