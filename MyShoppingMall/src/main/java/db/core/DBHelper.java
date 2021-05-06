package db.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {
    public static Connection getConnection() {
        Properties prop = new Properties();
        try {
            InputStream input = DBHelper.class.getClassLoader().getResourceAsStream("config.properties");

            prop.load(input);
            input.close();;
            String JDBC_DRIVER = prop.getProperty("JDBC_DRIVER");
            String PASS = prop.getProperty("PASS");
            String USER = prop.getProperty("USER");
            String DB_URL = prop.getProperty("DB_URL");
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return conn;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
