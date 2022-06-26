package lk.D24_Hostel.hostelSystem.bo.custom.impl;

import lk.D24_Hostel.hostelSystem.bo.custom.RoomBO;
import lk.D24_Hostel.hostelSystem.dao.DAOFactory;
import lk.D24_Hostel.hostelSystem.dao.custom.RoomDAO;
import lk.D24_Hostel.hostelSystem.dto.RoomDTO;
import lk.D24_Hostel.hostelSystem.entity.Room;

import java.util.ArrayList;
import java.util.List;

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
        return roomDAO.update(new Room(
                dto.getRoomTypeId(),
                dto.getType(),
                dto.getKeyMoney(),
                dto.getRoomQty()
        ));
    }

    @Override
    public boolean delete(String roomId) throws Exception {
        return roomDAO.delete(roomId);
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
