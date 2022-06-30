package lk.D24_Hostel.hostelSystem.bo.custom;

import lk.D24_Hostel.hostelSystem.bo.SuperBo;
import lk.D24_Hostel.hostelSystem.dto.UserDTO;

import java.util.List;

public interface UserBO extends SuperBo {
    boolean saveUser(UserDTO dto) throws Exception;

    boolean updateUser(UserDTO dto) throws Exception;

    boolean deleteUser(String s) throws Exception;

    List<UserDTO> getAllUsers() throws Exception;

    UserDTO loginValidation(String userName) throws Exception;
}
