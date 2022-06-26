package lk.D24_Hostel.hostelSystem.bo.custom.impl;

import lk.D24_Hostel.hostelSystem.bo.custom.StudentBO;
import lk.D24_Hostel.hostelSystem.dao.DAOFactory;
import lk.D24_Hostel.hostelSystem.dao.custom.StudentDAO;
import lk.D24_Hostel.hostelSystem.dto.StudentDTO;
import lk.D24_Hostel.hostelSystem.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean save(StudentDTO dto) throws Exception {
        return studentDAO.add(new Student(
                dto.getStudentId(),
                dto.getName(),
                dto.getAddress(),
                dto.getContactNo(),
                dto.getDob(),
                dto.getGender()
        ));
    }

    @Override
    public boolean update(StudentDTO dto) throws Exception {
        return studentDAO.update(new Student(
                dto.getStudentId(),
                dto.getName(),
                dto.getAddress(),
                dto.getContactNo(),
                dto.getDob(),
                dto.getGender()
        ));
    }

    @Override
    public boolean delete(String studentId) throws Exception {
        return studentDAO.delete(studentId);
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
}
