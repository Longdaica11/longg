package com.example.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseLogin {
    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "qlbh";
        String databaseUser = "root";
        String databasePassword = "123456a@";
        String url = "jdbc:mysql://localhost:3306/qlbh";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return databaseLink;
    }
}
