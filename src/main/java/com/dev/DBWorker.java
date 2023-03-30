package com.dev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWorker {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";

    private final Connection connection;

    public DBWorker(){
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void AddUserToDB(String username, String password){
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute("INSERT INTO users(username, password) VALUES('"+ username +"','"+ password+ "');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
