package com.example.db.core;

import java.sql.*;

public class WholeProcess {
    private static String url = "jdbc:mysql://localhost:3306/test";
    private static String user = "root";
    private static String pass = "zhq19970214";
    void method1() {
        throw new RuntimeException("Error");
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        new WholeProcess().method1();;
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, user, pass);
        String command = "Create Table  if not exists Persons (PersonID int, LastName varchar(255), FirstName varchar(255), Address varchar(255), City varchar(255));";
        PreparedStatement preStat = conn.prepareStatement(command);
        preStat.executeUpdate();
        String insertComm = "Insert into Persons (PersonID, LastName, FirstName, Address, City) Values (?, ?, ?, ?, ?)";

        preStat = conn.prepareStatement(insertComm);
        preStat.setInt(1, 1);
        preStat.setString(2, "Hangquan" );
        preStat.setString(3, "Zhao");
        preStat.setString(4, "La Jolla");
        preStat.setString(5, "San Diego");
        preStat.executeUpdate();

        String queryCommand = "select * from Persons where Personid = 1";
        preStat = conn.prepareStatement(queryCommand);
        ResultSet  resultSet = preStat.executeQuery();
        while (resultSet.next()) {
            System.out.println("PersonID: " + String.valueOf(resultSet.getInt(1)));
            System.out.println("LastName: " + String.valueOf(resultSet.getString(2)));
        }
    }
}
