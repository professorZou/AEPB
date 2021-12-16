package com.example.AEPB.exception;

public class NoSpaceException extends RuntimeException {
    public NoSpaceException(String code){
        super(code);
    }
}
