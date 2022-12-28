package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl usi = new UserServiceImpl();
        usi.createUsersTable();
        usi.saveUser("Oleg", "Zabo", Byte.parseByte("38"));
        System.out.println("User с именем Oleg добавлен в базу данных");
        usi.saveUser("Mirello", "Zabo", Byte.parseByte("4"));
        System.out.println("User с именем Mirello добавлен в базу данных");
        usi.saveUser("Danillo", "Zabo", Byte.parseByte("1"));
        System.out.println("User с именем Danillo добавлен в базу данных");
        usi.saveUser("Elena", "Beze", Byte.parseByte("38"));
        System.out.println("User с именем Elena добавлен в базу данных");
        List<User> allUsers = usi.getAllUsers();
        allUsers.forEach(System.out::println);
        usi.cleanUsersTable();
        usi.dropUsersTable();
    }
}
