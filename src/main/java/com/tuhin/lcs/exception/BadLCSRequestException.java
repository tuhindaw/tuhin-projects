package com.tuhin.lcs.exception;

public class BadLCSRequestException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    public BadLCSRequestException(){}

    public BadLCSRequestException(String message){
        super(message);
    }
}
