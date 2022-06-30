package lk.D24_Hostel.hostelSystem.dao.custom;

import javafx.collections.ObservableList;
import lk.D24_Hostel.hostelSystem.dao.CrudDAO;
import lk.D24_Hostel.hostelSystem.entity.Reservation;
import lk.D24_Hostel.hostelSystem.view.tdm.RemainKeyMoneyTM;

public interface ReservationDAO extends CrudDAO<Reservation, String> {
    ObservableList<RemainKeyMoneyTM> getRemainKeyMoneyStudents() throws Exception;
}
