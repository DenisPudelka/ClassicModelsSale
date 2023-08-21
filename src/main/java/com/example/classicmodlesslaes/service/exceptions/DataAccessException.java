package com.example.classicmodlesslaes.service.exceptions;

public class DataAccessException extends RuntimeException{
    public DataAccessException(String message, Throwable cause){
        super(message, cause);
    }
}
