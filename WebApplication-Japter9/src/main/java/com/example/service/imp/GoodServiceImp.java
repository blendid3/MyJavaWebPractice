package com.example.service.imp;

import com.example.dao.GoodDao;
import com.example.dao.imp.GoodImp;
import com.example.domain.Good;
import com.example.service.GoodService;

import java.util.List;

public class GoodServiceImp implements GoodService {
    GoodDao goodsDao = new GoodImp();

    @Override
    public List<Good> queryAll() {
        return goodsDao.findAll();

    }

    @Override
    public List<Good> queryByStartEnd(int start, int end) {
        return goodsDao.findStartEnd(start, end);
    }

    @Override
    public Good querDetail(long goodsid) {
        return null;
    }

    @Override
    public Good queryDetail(int goodsid) {
        return goodsDao.findByPk(goodsid);
    }
}
