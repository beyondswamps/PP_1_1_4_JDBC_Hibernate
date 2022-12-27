package jm.task.core.jdbc.util;

import javax.swing.event.MouseInputListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private Util() {};

    private static volatile Util instance;
    String url = "jdbc:mysql://localhost:3306/database";
    String user = "root";
    String password = "123456Qw*";

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
        };
        return connection;
    }
}
