package com.example.dao.imp;

import com.example.dao.CustomerDao;
import com.example.domain.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerImpTest {
    CustomerDao dao;
    @Test
    void findBypk() {
        Customer customer = this.dao.findBypk("10");
        assertNotNull(customer);
//        assertEquals();
        Assertions.assertEquals("10", customer.getId());
//        Assertions.assertEquals("");
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void remove() {
    }

    @Test
    void modify() {
    }

    @BeforeEach
    void setUp() {
        this.dao = new CustomerImp();

    }

    @AfterEach
    void tearDown() {
        this.dao = null;
    }
}