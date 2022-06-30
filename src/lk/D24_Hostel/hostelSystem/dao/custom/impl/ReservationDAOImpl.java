package lk.D24_Hostel.hostelSystem.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.D24_Hostel.hostelSystem.dao.custom.ReservationDAO;
import lk.D24_Hostel.hostelSystem.db.HibernateUtil;
import lk.D24_Hostel.hostelSystem.entity.Reservation;
import lk.D24_Hostel.hostelSystem.view.tdm.RemainKeyMoneyTM;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public boolean add(Reservation entity) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Reservation entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public Reservation find(String s) throws Exception {
        return null;
    }

    @Override
    public List<Reservation> findAll() throws Exception {
        return null;
    }

    @Override
    public String generateNewId() throws Exception {
        return null;
    }

    @Override
    public ObservableList<RemainKeyMoneyTM> getRemainKeyMoneyStudents() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        ObservableList<RemainKeyMoneyTM> remain = FXCollections.observableArrayList();
        List<Reservation> reservations = session.createQuery("FROM Reservation WHERE status = 'Unpaid'").list();

        for (Reservation r : reservations) {
            String id = r.getStudent().getStudent_id();
            String name = r.getStudent().getName();
            String status = r.getStatus();

            remain.add(new RemainKeyMoneyTM(id,name,status));
        }
        transaction.commit();
        session.close();
        return remain;
    }
}
