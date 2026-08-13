package com.example.ProjectPulse.Exceptions;

public class ProjectNotFoundException extends RuntimeException{
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public ProjectNotFoundException(String message){
        this.message = message;
    }
}
