package smallPracticeModule;

import db.core.DBHelper;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBTest {
//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        String PASS="zhq19970214";
//        String USER="root";
//        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
////        String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
//        String DB_URL="jdbc:mysql://localhost:3306/test";
//
//        Class.forName(JDBC_DRIVER);
//        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//        Statement stmt = conn.createStatement();
//        String sql = "select id from customers";
//        ResultSet rs = stmt.executeQuery(sql);
//        if (rs.next()) {
//            int id = rs.getInt("id");
//            System.out.println("id: " + String.valueOf(id));
//        }
//
//    }
    public static void DBTestFn() throws ClassNotFoundException, SQLException, IOException {
        Properties prop = new Properties();
        InputStream input = DBTest.class.getClassLoader().getResourceAsStream("config.properties");
        prop.load(input);

        System.out.println(prop.getProperty("JDBC_DRIVER"));
        String PASS="zhq19970214";
        String USER="root";
//        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
        String DB_URL="jdbc:mysql://localhost:3306/test";

        Class.forName(JDBC_DRIVER);
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        String sql = "select id from customers";
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            int id = rs.getInt("id");
            System.out.println("id: " + String.valueOf(id));
        }
    }

}
