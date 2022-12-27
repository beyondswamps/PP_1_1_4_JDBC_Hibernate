package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/database";
        String user = "root";
        String password = "123456Qw*";
        Statement statement;
        ResultSet resultSet;
        PreparedStatement preparedStatement;
        UserServiceImpl usi = new UserServiceImpl();
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connection established.");
//            preparedStatement = conn.prepareStatement("CREATE TABLE users (id INT AUTO_INCREMENT UNIQUE, name VARCHAR(30) NOT NULL, lastname VARCHAR(30) NOT NULL, age INT NOT NULL)");
//            preparedStatement.executeUpdate();
//            preparedStatement = conn.prepareStatement("INSERT INTO users (name, lastname, age) VALUES ('Olegman', 'Hero', 21)");
//            preparedStatement.executeUpdate();
//            statement = conn.createStatement();
//            resultSet = statement.executeQuery("select * from users");
//            resultSet.next();
//            System.out.println(resultSet.getString("name"));
//            System.out.println(resultSet.getString("lastname"));
            usi.createUsersTable();
            usi.saveUser("Oleg", "Zabo", Byte.valueOf("38"));
            usi.saveUser("Mirello", "Zabo", Byte.valueOf("4"));
            usi.saveUser("Danillo", "Zabo", Byte.valueOf("1"));
            List<User> lu = usi.getAllUsers();
            lu.stream().forEach(System.out::println);


        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        };
    }
}
