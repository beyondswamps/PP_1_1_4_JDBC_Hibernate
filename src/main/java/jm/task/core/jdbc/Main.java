package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl usi = new UserServiceImpl();

        usi.createUsersTable();
        usi.dropUsersTable();
        usi.saveUser("Oleg", "Zabo", Byte.parseByte("38"));
        usi.saveUser("Mirello", "Zabo", Byte.parseByte("4"));
        usi.saveUser("Danillo", "Zabo", Byte.parseByte("1"));
        List<User> lu = usi.getAllUsers();
        lu.forEach(System.out::println);
    }
}
