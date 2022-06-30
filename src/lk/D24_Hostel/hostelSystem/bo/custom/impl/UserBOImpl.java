package lk.D24_Hostel.hostelSystem.bo.custom.impl;

import lk.D24_Hostel.hostelSystem.bo.custom.UserBO;
import lk.D24_Hostel.hostelSystem.dao.DAOFactory;
import lk.D24_Hostel.hostelSystem.dao.custom.UserDAO;
import lk.D24_Hostel.hostelSystem.dto.UserDTO;
import lk.D24_Hostel.hostelSystem.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    private final UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean saveUser(UserDTO dto) throws Exception {
        return userDAO.add(new User(
                dto.getUserId(),
                dto.getUserName(),
                dto.getPassword()
        ));
    }

    @Override
    public boolean updateUser(UserDTO dto) throws Exception {
        return userDAO.update(new User(
                dto.getUserId(),
                dto.getUserName(),
                dto.getPassword()
        ));
    }

    @Override
    public boolean deleteUser(String s) throws Exception {
        return userDAO.delete(s);
    }

    @Override
    public List<UserDTO> getAllUsers() throws Exception {
        List<User> all = userDAO.findAll();
        ArrayList<UserDTO> allUsers = new ArrayList<>();

        for(User u : all){
            allUsers.add(new UserDTO(u.getUser_id(),u.getUser_name(),u.getPassword()));
        }
        return allUsers;
    }

    @Override
    public UserDTO loginValidation(String userName, String password) throws Exception {
        User ent = userDAO.LoginValidation(userName,password);
        return new UserDTO(ent.getUser_id(), ent.getUser_name(),ent.getPassword());
    }
}
