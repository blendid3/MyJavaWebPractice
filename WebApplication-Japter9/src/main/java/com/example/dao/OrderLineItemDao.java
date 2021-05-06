package com.example.dao;

import com.example.domain.Order;
import com.example.domain.OrderLineItem;

import java.util.List;

public interface OrderLineItemDao {
    void modify(Order o1);
    void create(Order o1);
    void remove(int Pk);
    OrderLineItem findByPk();
    List<OrderLineItem> findAll();

}
