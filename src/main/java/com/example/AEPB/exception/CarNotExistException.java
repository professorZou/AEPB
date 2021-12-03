package com.example.AEPB.exception;

public class CarNotExistException extends RuntimeException{
    public CarNotExistException(String code){
        super(code);
    }
}
