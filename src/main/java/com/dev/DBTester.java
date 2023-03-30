package com.dev;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTester {
    public static void main(String[] args) {
        DBWorker worker = new DBWorker();

        String query = "select * from users";
        try {
            Statement statement = worker.getConnection().createStatement();

            try{
                worker.AddUserToDB("John", "abc");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                User user = new User(resultSet.getInt("id"),
                                     resultSet.getString("username"),
                                     resultSet.getString("password"));

                System.out.println(user);
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
