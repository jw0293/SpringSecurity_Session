package com.example.security.springsecurity.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String userEmail){
        super(userEmail + " NotFoundException");
    }
}
