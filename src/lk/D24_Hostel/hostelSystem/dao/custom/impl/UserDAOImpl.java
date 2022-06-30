package lk.D24_Hostel.hostelSystem.dao.custom.impl;

import lk.D24_Hostel.hostelSystem.dao.custom.UserDAO;
import lk.D24_Hostel.hostelSystem.db.HibernateUtil;
import lk.D24_Hostel.hostelSystem.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean add(User entity) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User entity) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User user = session.load(User.class, s);
        session.delete(user);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public User find(String s) throws Exception {
        return null;
    }

    @Override
    public List<User> findAll() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String getAll = "FROM User";
        Query query = session.createQuery(getAll);
        List<User> user = query.list();

        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public String generateNewId() throws Exception {
        return null;
    }

    @Override
    public User LoginValidation(String useName, String password) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String find = "FROM User WHERE user_name = :userName AND password = :pwd";
        Query query = session.createQuery(find);
        query.setParameter("userName",useName);
        query.setParameter("pwd",password);

        transaction.commit();
        session.close();
        return null;
    }
}
