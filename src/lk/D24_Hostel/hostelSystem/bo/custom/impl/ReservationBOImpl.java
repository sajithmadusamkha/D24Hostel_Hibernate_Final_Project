package lk.D24_Hostel.hostelSystem.bo.custom.impl;

import lk.D24_Hostel.hostelSystem.bo.custom.ReservationBO;
import lk.D24_Hostel.hostelSystem.dao.DAOFactory;
import lk.D24_Hostel.hostelSystem.dao.custom.ReservationDAO;
import lk.D24_Hostel.hostelSystem.dao.custom.RoomDAO;
import lk.D24_Hostel.hostelSystem.dao.custom.StudentDAO;
import lk.D24_Hostel.hostelSystem.dto.ReservationDTO;
import lk.D24_Hostel.hostelSystem.dto.RoomDTO;
import lk.D24_Hostel.hostelSystem.dto.StudentDTO;
import lk.D24_Hostel.hostelSystem.entity.Room;
import lk.D24_Hostel.hostelSystem.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {

    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ROOM);
    private final ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RESERVATION);

    @Override
    public boolean saveReservation(ReservationDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean updateReservation(ReservationDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean deleteReservation(String bookingId) throws Exception {
        return false;
    }

    @Override
    public List<ReservationDTO> getAllReservation() throws Exception {
        return null;
    }

    @Override
    public List<StudentDTO> getAllStudents() throws Exception {
        List<Student> all = studentDAO.findAll();
        ArrayList<StudentDTO> allStudents = new ArrayList<>();

        for(Student s: all){
            allStudents.add(new StudentDTO(s.getStudent_id(),s.getName(),s.getAddress(),s.getContact_no(),s.getDob(),s.getGender()));
        }

        return allStudents;
    }

    @Override
    public List<RoomDTO> getAllRooms() throws Exception {
        List<Room> all = roomDAO.findAll();
        ArrayList<RoomDTO> allRooms = new ArrayList<>();

        for (Room r : all) {
            allRooms.add(new RoomDTO(r.getRoom_type_id(),r.getType(),r.getKey_money(),r.getRoom_qty()));
        }
        return allRooms;
    }
}
