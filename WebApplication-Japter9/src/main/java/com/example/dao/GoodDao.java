package com.example.dao;

import com.example.domain.Good;

import java.util.List;

public interface GoodDao {
    void modify(Good good);
    void remove(Good good);
    Good findByPk(int key );
    List<Good> findAll();
    void create(Good good);
    public List<Good> findStartEnd(int start, int end);
}
