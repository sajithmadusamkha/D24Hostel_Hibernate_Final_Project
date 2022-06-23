package lk.D24_Hostel.hostelSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room {
    @Id
    private String room_type_id;
    private String type;
    private double key_money;
    private Integer room_qty;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<>();

    public Room(String room_type_id, String type, double key_money, Integer room_qty) {
        this.room_type_id = room_type_id;
        this.type = type;
        this.key_money = key_money;
        this.room_qty = room_qty;
    }
}
