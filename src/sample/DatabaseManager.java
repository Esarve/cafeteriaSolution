package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseManager {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public void DBconnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/cafeteria", "root", "");
            System.out.printf("Connected!");
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
