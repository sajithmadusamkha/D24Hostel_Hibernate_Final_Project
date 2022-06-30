package lk.D24_Hostel.hostelSystem.bo.custom;

import javafx.collections.ObservableList;
import lk.D24_Hostel.hostelSystem.bo.SuperBo;
import lk.D24_Hostel.hostelSystem.dto.ReservationDTO;
import lk.D24_Hostel.hostelSystem.dto.RoomDTO;
import lk.D24_Hostel.hostelSystem.dto.StudentDTO;
import lk.D24_Hostel.hostelSystem.entity.Student;
import lk.D24_Hostel.hostelSystem.view.tdm.RemainKeyMoneyTM;

import java.util.List;

public interface ReservationBO extends SuperBo {
    boolean saveReservation(ReservationDTO dto) throws Exception;

    boolean updateReservation(ReservationDTO dto) throws Exception;

    boolean deleteReservation(String bookingId) throws Exception;

    List<ReservationDTO> getAllReservation() throws Exception;

    StudentDTO searchStudent(String id) throws Exception;

    RoomDTO searchRoom(String id) throws Exception;

    List<StudentDTO> getAllStudents() throws Exception;

    List<RoomDTO> getAllRooms() throws Exception;

    ObservableList<RemainKeyMoneyTM> getRemainKeyMoneyStudents() throws Exception;
}
