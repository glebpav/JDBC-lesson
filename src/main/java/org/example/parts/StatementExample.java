package org.example.parts;

import java.sql.*;

public class StatementExample {

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

            String insertSQL1 = "INSERT INTO students (name, age) VALUES ('Alice', 20)";
            String insertSQL2 = "INSERT INTO students (name, age) VALUES ('Bob', 22)";
            String insertSQL3 = "INSERT INTO students (name, age) VALUES ('Charlie', 23)";

            statement.executeUpdate(insertSQL1);
            statement.executeUpdate(insertSQL2);
            statement.executeUpdate(insertSQL3);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
