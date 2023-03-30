package com.dev;

import java.sql.*;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";
    public static void main(String[] args) throws SQLException {
        Connection connection;

        //Driver driver = new com.mysql.jdbc.Driver();
        //DriverManager.registerDriver(driver);

        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        if (!connection.isClosed()){
            System.out.println("Соединение с БД установлено");
        }

        Statement statement = connection.createStatement();
        //statement.execute("INSERT INTO animals(anim_name, anim_desc) VALUES('name','desc');");

        //int res = statement.executeUpdate("UPDATE animals SET anim_name = 'wolf' WHERE id = 1;");
        //System.out.println(res);

        //ResultSet resSet = statement.executeQuery("SELECT * FROM animals");

        statement.addBatch("INSERT INTO animals(anim_name, anim_desc) VALUES('animal1','desc');");
        statement.addBatch("INSERT INTO animals(anim_name, anim_desc) VALUES('animal2','desc');");
        statement.addBatch("INSERT INTO animals(anim_name, anim_desc) VALUES('animal3','desc');");

        statement.executeBatch();
        statement.clearBatch();

        boolean status = statement.isClosed();
        System.out.println(status);

        connection.close();
    }
}