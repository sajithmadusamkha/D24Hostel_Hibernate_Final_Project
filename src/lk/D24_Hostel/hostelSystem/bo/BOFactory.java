package lk.D24_Hostel.hostelSystem.bo;

import lk.D24_Hostel.hostelSystem.bo.custom.impl.ReservationBOImpl;
import lk.D24_Hostel.hostelSystem.bo.custom.impl.RoomBoImpl;
import lk.D24_Hostel.hostelSystem.bo.custom.impl.StudentBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance(){
        return (null == boFactory) ? boFactory = new BOFactory() : boFactory;
    }

    public SuperBo getBO(BOTypes types){
        switch (types){
            case STUDENT:
                return new StudentBOImpl();
            case ROOM:
                return new RoomBoImpl();
            case RESERVATION:
                return new ReservationBOImpl();
            default:
                return null;
        }
    }

    public enum BOTypes{
        STUDENT,ROOM,RESERVATION
    }
}
