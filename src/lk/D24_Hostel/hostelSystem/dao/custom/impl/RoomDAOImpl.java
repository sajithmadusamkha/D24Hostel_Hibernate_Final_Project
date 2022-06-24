package lk.D24_Hostel.hostelSystem.dao.custom.impl;

import lk.D24_Hostel.hostelSystem.dao.custom.RoomDAO;
import lk.D24_Hostel.hostelSystem.db.HibernateUtil;
import lk.D24_Hostel.hostelSystem.entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RoomDAOImpl implements RoomDAO {

    @Override
    public boolean add(Room entity) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Room entity) throws Exception {
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

        Room room = session.load(Room.class, s);
        session.delete(room);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Room find(String s) throws Exception {
        return null;
    }

    @Override
    public List<Room> findAll() throws Exception {
        return null;
    }
}
