package org.example.parts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args)  {

        String url = "jdbc:postgresql://localhost:5432/students_db";
        String user = "postgres";
        String password = "admin";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Подключение успешно!");

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}