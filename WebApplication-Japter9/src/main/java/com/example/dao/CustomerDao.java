package com.example.dao;

import com.example.domain.Customer;

import java.util.List;

public interface CustomerDao {
    Customer findBypk(String Pk);
    List<Customer> findAll();
    void create(Customer c1);
    void remove(Customer customer);
    void modify(Customer c1);

}
