package db.dao;

import db.domain.Customer;

import java.sql.SQLException;

public interface CustomerDao {
    void insert(Customer customer);
    boolean findAll(Customer customer) throws SQLException;
    boolean findAll(int id, String password) throws SQLException;
    Customer getById(int id) throws SQLException;

}
