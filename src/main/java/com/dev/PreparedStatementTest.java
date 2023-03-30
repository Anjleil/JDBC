package com.dev;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Calendar;

public class PreparedStatementTest {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";
    public static final String INSERT_NEW = "INSERT INTO dish VALUES(?,?,?,?,?,?,?)";
    public static final String GET_ALL = "SELECT * FROM dish";
    public static final String DELETE = "DELETE FROM dish WHERE id = ?";
    public static void main(String[] args) {
        Connection connection;
        PreparedStatementTest worker = new PreparedStatementTest();

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            //worker.InsertToDB(connection);
            worker.DeleteFromDB(connection, 3);
            worker.getAll(connection);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void getAll(Connection connection){
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String desc = resultSet.getString("description");
                float rate = resultSet.getFloat("rating");
                boolean published = resultSet.getBoolean("published");
                Date date = resultSet.getDate("created");
                byte[] icon = resultSet.getBytes("icon");

                System.out.println("id: "+id+", title: "+title+", desc: "+desc
                +", rate: "+rate+", published: "+published+", date: "+date);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void DeleteFromDB(Connection connection, int id){
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void InsertToDB(Connection connection){
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(INSERT_NEW);

            preparedStatement.setInt(1,3);
            preparedStatement.setString(2, "title");
            preparedStatement.setString(3, "desc");
            preparedStatement.setFloat(4, 0.2f);
            preparedStatement.setBoolean(5, true);
            preparedStatement.setDate(6, new Date(Calendar.getInstance().getTimeInMillis()));
            preparedStatement.setBlob(7, new FileInputStream("src/main/resources/cat.png"));

            preparedStatement.execute();
        } catch (SQLException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
