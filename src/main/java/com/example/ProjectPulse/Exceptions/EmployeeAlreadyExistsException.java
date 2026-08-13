package com.example.ProjectPulse.Exceptions;

public class EmployeeAlreadyExistsException extends RuntimeException{

   private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public EmployeeAlreadyExistsException(String message){
        this.message = message;
    }
}
