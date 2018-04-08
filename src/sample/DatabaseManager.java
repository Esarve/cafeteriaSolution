package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseManager {

    public static Connection DBconnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/cafeteria", "root", "");
            System.out.printf("Connected!");
            return connection;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}
