package org.example.full;

import org.example.full.dao.StudentDao;
import org.example.full.model.Student;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        StudentDao studentDAO = new StudentDao();

        try {
            studentDAO.createTable();
            System.out.println("Таблица создана/проверена.");

            studentDAO.addStudent(new Student("Alice", 20));
            studentDAO.addStudent(new Student("Bob", 22));
            studentDAO.addStudent(new Student("Charlie", 23));
            System.out.println("Студенты добавлены.");

            System.out.println("\nСписок студентов:");
            studentDAO.getAllStudents().forEach(System.out::println);

            System.out.println("\nОбновление возраста Alice:");
            studentDAO.updateStudentAge(1, 25);
            studentDAO.getAllStudents().forEach(System.out::println);

            System.out.println("\nУдаление Bob:");
            studentDAO.deleteStudent(2);
            studentDAO.getAllStudents().forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
