package lk.D24_Hostel.hostelSystem.dao;

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
                return null;
            case RESERVATION:
                return null;
            default:
                return null;
        }
    }

    public enum DAOTypes{
        STUDENT,ROOM,RESERVATION
    }
}