package com.example.db.core;

public class DataAccessException extends RuntimeException{
    public DataAccessException(String mess) {
        super(mess);
    }
    public DataAccessException(String mess, Throwable ex) {
        super(mess, ex);
    }

}
