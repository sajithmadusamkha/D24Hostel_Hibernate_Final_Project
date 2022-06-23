package lk.D24_Hostel.hostelSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private String roomTypeId;
    private String type;
    private double keyMoney;
    private Integer roomQty;
}
