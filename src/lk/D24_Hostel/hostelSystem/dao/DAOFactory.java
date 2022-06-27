package lk.D24_Hostel.hostelSystem.dao;

import lk.D24_Hostel.hostelSystem.dao.custom.impl.ReservationDAOImpl;
import lk.D24_Hostel.hostelSystem.dao.custom.impl.RoomDAOImpl;
import lk.D24_Hostel.hostelSystem.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return (null == daoFactory) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes{
        STUDENT,ROOM,RESERVATION
    }
}
