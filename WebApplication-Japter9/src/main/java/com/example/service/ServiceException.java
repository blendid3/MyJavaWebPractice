package com.example.service;

public class ServiceException extends Exception{
    public ServiceException(String mess) {
        super(mess);
    }
    ServiceException(String mess, Exception e) {
        super(mess, e);
    }
}
