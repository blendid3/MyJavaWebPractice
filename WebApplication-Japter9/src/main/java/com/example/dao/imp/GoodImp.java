package com.example.dao.imp;

import com.example.dao.GoodDao;
import com.example.db.core.DataAccessException;
import com.example.db.core.JdbcTemplate;
import com.example.db.core.PreparedStatementCreator;
import com.example.db.core.RowCallbackHandler;
import com.example.domain.Good;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodImp implements GoodDao {
    @Override
    public void modify(Good good) {
        JdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn)  {
                String selectSql = "update Goods set name = ?, price = ?,  description = ?, brand = ?, cpu_brand = ?, cpu_type = ?, memory_capacity= ?, hd_capacity= ?, card_model= ?, display_size = ?, image = ? where id = ?";
                try {
                    PreparedStatement ps = conn.prepareStatement(selectSql);
                    ps.setString(1, good.getName());
                    ps.setFloat(2, good.getPrice());
                    ps.setString(3, good.getDescription());
                    ps.setString(4, good.getBrand());
                    ps.setString(5, good.getCpu_brand());
                    ps.setString(6, good.getCpu_type());
                    ps.setString(7, good.getMemory_capacity());
                    ps.setString(8, good.getHd_capacity());
                    ps.setString(9, good.getCard_model());
                    ps.setString(10, good.getDisplaysize());
                    ps.setString(11, good.getImage());
                    ps.setInt(12, good.getId());
                    return ps;
                } catch (SQLException e) {
                    throw new DataAccessException("findAll: createPreparedStatement Exception", e);
                }
            }
        });
    }

    @Override
    public void remove(Good good) {
        JdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn)  {
                String selectSql = "delete from Goods where id = ?";
                try {
                    PreparedStatement ps = conn.prepareStatement(selectSql);
                    ps.setInt(1, good.getId());
                    return ps;
                } catch (SQLException e) {
                    throw new DataAccessException("findAll: createPreparedStatement Exception", e);
                }
            }
        });
    }

    @Override
    public Good findByPk(int key) {
        List<Good> l1 = new ArrayList<>();
        JdbcTemplate.query(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection conn) {
                String selectSql = "select * from Goods where id = ?";
                try {
                    PreparedStatement ps = conn.prepareStatement(selectSql);
                    ps.setInt(1, key);
                    return ps;
                } catch (SQLException e) {
                    throw new DataAccessException("findAll: createPreparedStatement Exception", e);
                }
            }
        }, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) {
                try {
                    Good g1 = new Good();
                    g1.setId(rs.getInt("id"));
                    g1.setName(rs.getString("name"));
                    g1.setPrice(rs.getFloat("price"));
                    g1.setDescription(rs.getString("description"));
                    g1.setBrand(rs.getString("brand"));
                    g1.setCpu_brand(rs.getString("cpu_brand"));
                    g1.setCpu_type(rs.getString("cpu_type"));
                    g1.setMemory_capacity(rs.getString("memory_capacity"));
                    g1.setHd_capacity(rs.getString("hd_capacity"));
                    g1.setCard_model(rs.getString("card_model"));
                    g1.setDisplaysize(rs.getString("displaysize"));
                    g1.setImage(rs.getString("image"));
                } catch (SQLException throwables) {
                    throw new DataAccessException("GoodImp: findByPK: processRow: Exception", throwables);
                }
            }
        });
        return l1.size() == 0? null: l1.get(0);
    }

    @Override
    public List<Good> findAll() {

        List<Good> l1 = new ArrayList<>();
        JdbcTemplate.query(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection conn) {
                String selectSql = "select * from Goods";
                try {
                    PreparedStatement ps = conn.prepareStatement(selectSql);
                    return ps;
                } catch (SQLException e) {
                    throw new DataAccessException("findAll: createPreparedStatement Exception", e);
                }
            }
        }, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) {
                try {
                    Good g1 = new Good();
                    g1.setId(rs.getInt("id"));
                    g1.setName(rs.getString("name"));
                    g1.setPrice(rs.getFloat("price"));
                    g1.setDescription(rs.getString("description"));
                    g1.setBrand(rs.getString("brand"));
                    g1.setCpu_brand(rs.getString("cpu_brand"));
                    g1.setCpu_type(rs.getString("cpu_type"));
                    g1.setMemory_capacity(rs.getString("memory_capacity"));
                    g1.setHd_capacity(rs.getString("hd_capacity"));
                    g1.setCard_model(rs.getString("card_model"));
                    g1.setDisplaysize(rs.getString("displaysize"));
                    g1.setImage(rs.getString("image"));
                    l1.add(g1);
                } catch (SQLException throwables) {
                    throw new DataAccessException("GoodImp: findByPK: processRow: Exception", throwables);
                }
            }
        });
        return l1;

    }

    @Override
    public void create(Good good) {
        List<Good> l1 = new ArrayList<>();
        JdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection conn) {

                String selectSql = "insert into Goods (id,name,price,description,brand,cpu_brand,cpu_type,memory_capacity,hd_capacity,card_model,displaysize,image) values (?,?,?,?,?,?,?,?,?,?,?,?)";
                try {
                    PreparedStatement ps = conn.prepareStatement(selectSql);
                    ps.setLong(1, good.getId());
                    ps.setString(2, good.getName());
                    ps.setDouble(3, good.getPrice());
                    ps.setString(4, good.getDescription());
                    ps.setString(5, good.getBrand());
                    ps.setString(6, good.getCpu_brand());
                    ps.setString(7, good.getCpu_type());
                    ps.setString(8, good.getMemory_capacity());
                    ps.setString(9, good.getHd_capacity());
                    ps.setString(10, good.getCard_model());
                    ps.setString(11, good.getDisplaysize());
                    ps.setString(12, good.getImage());
                    return ps;
                } catch (SQLException e) {
                    throw new DataAccessException("findAll: createPreparedStatement Exception", e);
                }
            }
        });
    }
    private void populate(List<Good> list, ResultSet rs) throws SQLException {
        Good goods = new Good();
        goods.setId(rs.getInt("id"));
        goods.setName(rs.getString("name"));
        goods.setPrice(rs.getFloat("price"));
        goods.setDescription(rs.getString("description"));
        goods.setBrand(rs.getString("brand"));
        goods.setCpu_brand(rs.getString("cpu_brand"));
        goods.setCpu_type(rs.getString("cpu_type"));
        goods.setMemory_capacity(rs.getString("memory_capacity"));
        goods.setHd_capacity(rs.getString("hd_capacity"));
        goods.setCard_model(rs.getString("card_model"));
        goods.setDisplaysize(rs.getString("displaysize"));
        goods.setImage(rs.getString("image"));

        list.add(goods);
    }

    @Override
    public List<Good> findStartEnd(int start, int end) {
        List<Good> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select id,name,price,description,brand,cpu_brand,cpu_type,memory_capacity,hd_capacity,card_model,displaysize,image from Goods");
        sql.append(" LIMIT ").append(end - start);
        sql.append(" OFFSET ").append(start);
        JdbcTemplate.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            return ps;
        }, rs -> {
            populate(list,rs);
        });
        return list;
    }
}
