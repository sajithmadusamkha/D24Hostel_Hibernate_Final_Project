package lk.D24_Hostel.hostelSystem.bo;

import lk.D24_Hostel.hostelSystem.bo.custom.impl.ReservationBOImpl;
import lk.D24_Hostel.hostelSystem.bo.custom.impl.RoomBoImpl;
import lk.D24_Hostel.hostelSystem.bo.custom.impl.StudentBOImpl;
import lk.D24_Hostel.hostelSystem.bo.custom.impl.UserBOImpl;

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
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }

    public enum BOTypes{
        STUDENT,ROOM,RESERVATION,USER
    }
}
