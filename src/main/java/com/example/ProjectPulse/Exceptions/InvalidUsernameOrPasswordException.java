package com.example.ProjectPulse.Exceptions;

public class InvalidUsernameOrPasswordException extends RuntimeException{
    @Override
    public String getMessage() {
        return message;
    }

    private String message;
    public InvalidUsernameOrPasswordException(String message){
        this.message = message;
    }

}
