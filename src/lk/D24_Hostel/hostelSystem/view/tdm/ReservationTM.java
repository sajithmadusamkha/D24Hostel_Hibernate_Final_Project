package lk.D24_Hostel.hostelSystem.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationTM {
    private String roomId;
    private String roomType;
    private Integer studentQry;
    private double keyMoney;
    private double total;
    private String status;
}
