package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private Util() {}

    private static volatile Util instance;
    private final String url = "jdbc:mysql://localhost:3306/db";
    private final String user = "root";
    private final String password = "123456Qw*";

    public static Util getInstance() {
        Util localInstance = instance;
        if (localInstance == null) {
            synchronized (Util.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Util();
                }
            }
        }
        return localInstance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException sqle) {
                sqle.printStackTrace();
        }
        return connection;
    }
}
