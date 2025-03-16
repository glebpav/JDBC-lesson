package org.example.parts;

import java.sql.*;

public class ResultSetExample {

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

            String insertSQL = "INSERT INTO students (name, age) VALUES (?, ?)";
            try (PreparedStatement insertStmt = connection.prepareStatement(insertSQL)) {
                insertStmt.setString(1, "Alice");
                insertStmt.setInt(2, 20);
                insertStmt.executeUpdate();

                insertStmt.setString(1, "Bob");
                insertStmt.setInt(2, 22);
                insertStmt.executeUpdate();

                insertStmt.setString(1, "Charlie");
                insertStmt.setInt(2, 53);
                insertStmt.executeUpdate();
                System.out.println("Данные добавлены.");
            }

            String selectSQL = "SELECT * FROM students";
            try (PreparedStatement selectStmt = connection.prepareStatement(selectSQL);
                 ResultSet rs = selectStmt.executeQuery()) {

                System.out.println("Список студентов:");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    System.out.printf("ID: %d | Имя: %s | Возраст: %d%n", id, name, age);
                }
            }

            String updateSQL = "UPDATE students SET age = ? WHERE name = ?";
            try (PreparedStatement updateStmt = connection.prepareStatement(updateSQL)) {
                updateStmt.setInt(1, 25);
                updateStmt.setString(2, "Alice");
                int updatedRows = updateStmt.executeUpdate();
                System.out.println("Обновлено строк: " + updatedRows);
            }

            String deleteSQL = "DELETE FROM students WHERE name = ?";
            try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSQL)) {
                deleteStmt.setString(1, "Bob");
                int deletedRows = deleteStmt.executeUpdate();
                System.out.println("Удалено строк: " + deletedRows);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
