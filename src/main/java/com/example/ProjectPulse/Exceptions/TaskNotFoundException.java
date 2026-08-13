package com.example.ProjectPulse.Exceptions;

public class TaskNotFoundException extends RuntimeException{
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public TaskNotFoundException(String message){
        this.message = message;
    }
}
