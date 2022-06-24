package lk.D24_Hostel.hostelSystem.bo.custom.impl;

import lk.D24_Hostel.hostelSystem.bo.custom.RoomBO;
import lk.D24_Hostel.hostelSystem.dao.DAOFactory;
import lk.D24_Hostel.hostelSystem.dao.custom.RoomDAO;
import lk.D24_Hostel.hostelSystem.dto.RoomDTO;
import lk.D24_Hostel.hostelSystem.entity.Room;

public class RoomBoImpl implements RoomBO {

    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public boolean save(RoomDTO dto) throws Exception {
        return roomDAO.add(new Room(
                dto.getRoomTypeId(),
                dto.getType(),
                dto.getKeyMoney(),
                dto.getRoomQty()
        ));
    }

    @Override
    public boolean update(RoomDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String roomId) throws Exception {
        return roomDAO.delete(roomId);
    }

}
