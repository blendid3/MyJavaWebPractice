package com.example.db.core;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelp {
    static Properties info = new Properties();
    private static String url = "jdbc:mysql://localhost:3306/test";
    private static String user = "root";
    private static String password = "zhq19970214";
//    static {
//        InputStream in = DBHelp.class.getClassLoader().getResourceAsStream("config.properties");
//        try {
//            info.load(in);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

   public static Connection getConnect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
//        Class.forName(info.getProperty("driver"));
//        Connection connection = DriverManager.getConnection(info.getProperty("url"), info);
       Connection connection = DriverManager.getConnection(url, user, password);
       return connection;
    }
}
