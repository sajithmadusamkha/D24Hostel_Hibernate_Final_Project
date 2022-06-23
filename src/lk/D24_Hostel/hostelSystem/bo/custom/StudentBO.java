package lk.D24_Hostel.hostelSystem.bo.custom;

import lk.D24_Hostel.hostelSystem.bo.SuperBo;
import lk.D24_Hostel.hostelSystem.dto.StudentDTO;

import java.sql.SQLException;
import java.util.List;

public interface StudentBO extends SuperBo {
    boolean save(StudentDTO dto) throws Exception;

    boolean update(StudentDTO dto) throws Exception;

    boolean delete(String studentId) throws Exception;

    List<StudentDTO> getAllStudents() throws Exception;
}
