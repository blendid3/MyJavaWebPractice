package com.example.service;

import com.example.domain.Good;

import java.util.List;

public interface GoodService {
    List<Good> queryAll();

    List<Good> queryByStartEnd(int start, int end);

    Good querDetail(long goodsid);

    Good queryDetail(int goodsid);
}
