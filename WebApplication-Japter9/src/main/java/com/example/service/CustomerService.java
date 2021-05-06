package com.example.service;

import com.example.domain.Customer;

public interface CustomerService {
    void register(Customer c1) throws ServiceException;
    boolean login(Customer c1);
}
