package lk.D24_Hostel.hostelSystem.bo.custom.impl;

import lk.D24_Hostel.hostelSystem.bo.custom.ReservationBO;
import lk.D24_Hostel.hostelSystem.dto.ReservationDTO;

import java.util.List;

public class ReservationBOImpl implements ReservationBO {
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
}
