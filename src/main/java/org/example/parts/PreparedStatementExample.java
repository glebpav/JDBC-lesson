package org.example.parts;

import java.sql.*;

public class PreparedStatementExample {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/students_db";
        String user = "postgres";
        String password = "admin";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Соединение установлено!");

            Statement statement = connection.createStatement();

            String createTableSQL = """
                    CREATE TABLE IF NOT EXISTS students (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(100),
                        age INT
                    )
                    """;
            statement.execute(createTableSQL);

            String truncateSQL = "TRUNCATE TABLE students RESTART IDENTITY";
            try (PreparedStatement truncateStmt = connection.prepareStatement(truncateSQL)) {
                truncateStmt.executeUpdate();
                System.out.println("Таблица очищена.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
