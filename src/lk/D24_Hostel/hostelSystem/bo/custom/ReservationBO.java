package lk.D24_Hostel.hostelSystem.bo.custom;

import lk.D24_Hostel.hostelSystem.bo.SuperBo;
import lk.D24_Hostel.hostelSystem.dto.ReservationDTO;
import lk.D24_Hostel.hostelSystem.dto.RoomDTO;
import lk.D24_Hostel.hostelSystem.dto.StudentDTO;

import java.util.List;

public interface ReservationBO extends SuperBo {
    boolean saveReservation(ReservationDTO dto) throws Exception;

    boolean updateReservation(ReservationDTO dto) throws Exception;

    boolean deleteReservation(String bookingId) throws Exception;

    List<ReservationDTO> getAllReservation() throws Exception;

    List<StudentDTO> getAllStudents() throws Exception;

    List<RoomDTO> getAllRooms() throws Exception;
}
