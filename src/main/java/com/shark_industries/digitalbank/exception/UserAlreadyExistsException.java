package com.shark_industries.digitalbank.exception;

public class UserAlreadyExistsException
        extends RuntimeException {

    private final String code;



    public UserAlreadyExistsException(String message, String code){
        //почему Super
        this.code = code;
        super(message) ;
    }

    public String getCode() {
        return code;
    }
}