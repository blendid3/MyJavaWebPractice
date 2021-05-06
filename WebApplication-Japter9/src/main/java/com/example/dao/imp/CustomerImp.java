package com.example.dao.imp;

import com.example.dao.CustomerDao;
import com.example.db.core.DataAccessException;
import com.example.db.core.JdbcTemplate;
import com.example.db.core.PreparedStatementCreator;
import com.example.db.core.RowCallbackHandler;
import com.example.domain.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerImp implements CustomerDao {
//    JdbcTemplate jdbc = new JdbcTemplate();
    @Override
    public Customer findBypk(String pk) {
        List<Customer> l1 = new ArrayList<>();
        JdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn)  {
                String selectSql = "select * from Customers where id = ?";
                try {
                    PreparedStatement ps = conn.prepareStatement(selectSql);
                    ps.setString(1, pk);
                    return ps;
                } catch (SQLException e) {
                    throw new DataAccessException("findBypk: createPreparedStatement Exception", e);
                }

            }
        }, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) {
                try {
                    Customer c1 = new Customer();

                    c1.setBirthday(new Date(rs.getLong("birthday")));
                    c1.setId(rs.getString("id"));
                    c1.setAddress(rs.getString("address"));
                    c1.setName(rs.getString("name"));
                    c1.setPhone(rs.getString("phone"));
                    c1.setPassword(rs.getString("password"));
                    l1.add(c1);
                } catch (SQLException e) {
                    throw new DataAccessException("findBypk: processRow Exception", e);
                }

            }
        });
        return l1.size() == 0 ? null: l1.get(0);
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> l1 = new ArrayList<>();
        JdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn)  {
                String selectSql = "select * from Customers ";
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
                    Customer c1 = new Customer();

                    c1.setBirthday(new Date(rs.getLong("birthday")));
                    c1.setId(rs.getString("id"));
                    c1.setAddress(rs.getString("address"));
                    c1.setName(rs.getString("name"));
                    c1.setPhone(rs.getString("phone"));
                    c1.setPassword(rs.getString("password"));
                    l1.add(c1);
                } catch (SQLException e) {
                    throw new DataAccessException("findAll: processRow Exception", e);
                }

            }
        });
        return l1.size() == 0 ? null: l1;
    }

    @Override
    public void create(Customer c1) {

        JdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn)  {
                String selectSql = "insert into Customers (id, name, password, address, phone, birthday) values (?, ?, ?, ?, ?, ?)";
                try {
                    PreparedStatement ps = conn.prepareStatement(selectSql);
                    ps.setString(1, c1.getId());
                    ps.setString(2, c1.getName());
                    ps.setString(3, c1.getPassword());
                    ps.setString(4, c1.getAddress());
                    ps.setLong(5,  c1.getBirthday().getTime());
                    return ps;
                } catch (SQLException e) {
                    throw new DataAccessException("findAll: createPreparedStatement Exception", e);
                }
            }
        });
    }

    @Override
    public void remove(Customer customer) {
        JdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn)  {
                String selectSql = "delete from Customers where id = ?";
                try {
                    PreparedStatement ps = conn.prepareStatement(selectSql);
                    ps.setString(1, customer.getId());
                    return ps;
                } catch (SQLException e) {
                    throw new DataAccessException("findAll: createPreparedStatement Exception", e);
                }
            }
        });
    }

    @Override
    public void modify(Customer c1) {
        JdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn)  {
                String selectSql = "update Customers set name = ?, password = ?, address = ?, phone = ?, birthday = ?" +
                        "where id = ?";
                try {
                    PreparedStatement ps = conn.prepareStatement(selectSql);
                    ps.setString(1, c1.getName());
                    ps.setString(2, c1.getPassword());
                    ps.setString(3, c1.getAddress());
                    ps.setString(4, c1.getPhone());
                    ps.setLong(5, c1.getBirthday().getTime());
                    ps.setString(6, c1.getId());
                    return ps;
                } catch (SQLException e) {
                    throw new DataAccessException("findAll: createPreparedStatement Exception", e);
                }
            }
        });
    }
}
