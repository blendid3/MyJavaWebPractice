package db.dao.imp;

import db.core.JDBCTemplate;
import db.dao.CustomerDao;
import db.domain.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CustomerDaoImp implements CustomerDao {
    @Override
    public void insert(Customer customer) {
        StringBuffer sql = new StringBuffer();
//        INSERT INTO table_name (column1, column2, column3, ...)
//        VALUES (value1, value2, value3, ...);
        String preInsert = "insert into Customers (id, name, password, address, phone, birthday) values (";
        String endInsert = ")";
        sql.append(preInsert);

        int id = customer.getId();
        sql.append(id); sql.append(",");
        String name = customer.getName();
        sql.append(pasrsToString(name)); sql.append(",");
        String password = customer.getPassword();
        sql.append(pasrsToString(password)); sql.append(",");
        String address = customer.getAddress();
        sql.append(pasrsToString(address)); sql.append(",");
        String phone = customer.getPhone();
        sql.append(pasrsToString(phone)); sql.append(",");

        SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd");

        Date birthday = customer.getBirthday();
        String bir_str =  sdfr.format(birthday);
        sql.append(pasrsToString(bir_str));
        sql.append(endInsert);

        JDBCTemplate.executeUpdate(sql.toString());
    }
/// mainly check id, name
    @Override
    public boolean findAll(Customer customer) throws SQLException {
        String name = customer.getName();
        int id = customer.getId();

        String sql = "select * from customers";
        ResultSet rs = JDBCTemplate.executeQuery(sql);
        while (rs.next()) {
            String recName = rs.getString("name");
            int recId = rs.getInt("id");
            if (id == recId || recName.equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean findAll(int id, String password) throws SQLException {
        String sql = "select * from customers";
        ResultSet rs = JDBCTemplate.executeQuery(sql);
        while (rs.next()) {
            String recpassword = rs.getString("password");
            int recId = rs.getInt("id");
            if (id == recId && recpassword.equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Customer getById(int id) throws SQLException {
        String sql = "select * from customers";
        ResultSet rs = JDBCTemplate.executeQuery(sql);
        while (rs.next()) {
            int recId = rs.getInt("id");
            if (id == recId ) {
                int rsid = rs.getInt("id");
                String rsName = rs.getString("name");
                String rsPass = rs.getString("password");
                String rsAdd = rs.getString("address");
                String rsPhone = rs.getString("phone");
                Date rsBir = rs.getDate("birthday");

                Customer customer = new Customer(rsid, rsName, rsPass, rsAdd, rsPhone, rsBir);
                return customer;
            }
        }
        return null;
    }

    String pasrsToString(String str) {
        return "'" + str + "'";
    }



}
