package lk.D24_Hostel.hostelSystem.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomTM implements Comparable<RoomTM> {
    private String roomTypeId;
    private String type;
    private double keyMoney;
    private Integer roomQty;

    @Override
    public int compareTo(RoomTM o) {
        return roomTypeId.compareTo(o.roomTypeId);
    }
}
