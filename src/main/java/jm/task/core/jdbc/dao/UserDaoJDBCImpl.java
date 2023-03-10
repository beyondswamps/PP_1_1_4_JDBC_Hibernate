package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS users(\n" +
                    "id INT AUTO_INCREMENT,\n" +
                    "name VARCHAR(40) NOT NULL,\n" +
                    "lastName VARCHAR(40) NOT NULL,\n" +
                    "age INT NOT NULL,\n" +
                    "PRIMARY KEY (id));\n");
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DROP TABLE IF EXISTS users");
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO users (name, lastName, age) values (?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setInt(3, age);
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE id=?");
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection = Util.getInstance().getConnection()) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT name, lastName, age from users");
            while (rs.next()) {
                list.add(new User(
                        rs.getString("name"),
                        rs.getString("lastName"),
                        rs.getByte("age")));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return list;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM users");
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
