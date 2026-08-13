package com.example.ProjectPulse.Exceptions;

public class ProjectAlreadyExistsException extends RuntimeException{
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public ProjectAlreadyExistsException(String message){
        this.message = message;
    }
}
