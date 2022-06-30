package lk.D24_Hostel.hostelSystem.dao.custom;

import lk.D24_Hostel.hostelSystem.dao.CrudDAO;
import lk.D24_Hostel.hostelSystem.entity.User;

public interface UserDAO extends CrudDAO<User, String> {
    User LoginValidation(String useName, String password) throws Exception;
}
