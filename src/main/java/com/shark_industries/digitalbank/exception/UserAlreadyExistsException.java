package com.shark_industries.digitalbank.exception;

public class UserAlreadyExistsException
        extends RuntimeException {


    public UserAlreadyExistsException(String message){
        //почему Super
        super(message);
    }

}