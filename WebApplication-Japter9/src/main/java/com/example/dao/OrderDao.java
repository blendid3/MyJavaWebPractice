package com.example.dao;

import com.example.domain.Order;

import java.util.List;

public interface OrderDao {
    Order findbyPk(String id);
    void modify(Order o1);
    void remove(String Pk);
    void create(Order o1);
    List<Order> findAll();
}
