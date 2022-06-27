package lk.D24_Hostel.hostelSystem.dto;

import lk.D24_Hostel.hostelSystem.entity.Room;
import lk.D24_Hostel.hostelSystem.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private String resId;
    private LocalDate date;
    private Student student;
    private Room room;
    private double key_money;
    private String status;
    private Integer qty;
}
