package lk.D24_Hostel.hostelSystem.bo.custom;

import lk.D24_Hostel.hostelSystem.bo.SuperBo;
import lk.D24_Hostel.hostelSystem.dto.RoomDTO;

public interface RoomBO extends SuperBo {
    boolean save(RoomDTO dto) throws Exception;

    boolean update(RoomDTO dto) throws Exception;

    boolean delete(String roomId) throws Exception;
}
