package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getInstance().getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS users(\n" +
                            "id INT AUTO_INCREMENT,\n" +
                            "name VARCHAR(40) NOT NULL,\n" +
                            "lastName VARCHAR(40) NOT NULL,\n" +
                            "age INT NOT NULL,\n" +
                            "PRIMARY KEY (id));\n")
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            if (transaction != null) {
                transaction.rollback();
            }
            hibernateException.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getInstance().getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users")
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            if (transaction != null) {
                transaction.rollback();
            }
            hibernateException.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getInstance().getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        } catch (HibernateException hibernateException) {
            if (transaction != null) {
                transaction.rollback();
            }
            hibernateException.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getInstance().getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            transaction.commit();
        } catch (HibernateException hibernateException) {
            if (transaction != null) {
                transaction.rollback();
            }
            hibernateException.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        List<User> list = null;
        Session session = Util.getInstance().getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            list = session.createQuery("from User").getResultList();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            if (transaction != null) {
                transaction.rollback();
            }
            hibernateException.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getInstance().getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createQuery("DELETE from User").executeUpdate();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            if (transaction != null) {
                transaction.rollback();
            }
            hibernateException.printStackTrace();
        }
    }
}
