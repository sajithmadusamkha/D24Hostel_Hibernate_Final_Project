package lk.D24_Hostel.hostelSystem.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.D24_Hostel.hostelSystem.bo.BOFactory;
import lk.D24_Hostel.hostelSystem.bo.custom.ReservationBO;
import lk.D24_Hostel.hostelSystem.dto.RoomDTO;
import lk.D24_Hostel.hostelSystem.dto.StudentDTO;

import java.util.ArrayList;
import java.util.List;

public class ReservationFormController {
    public ComboBox cmbStudentID;
    public ComboBox cmbRoomID;
    public TextField txtReserveId;
    public TextField txtStudentName;
    public TextField txtRoomType;
    public TextField txtKeyMoney;
    public TextField txtRoomQty;
    public TextField txtStatus;
    public TextField txtStudentQty;
    public TableView tblReservation;

    private final ReservationBO reservationBO = (ReservationBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RESERVATION);

    public void initialize(){
        loadAllStudentIDs();
        loadAllRoomIDs();
    }

    public void btnReserveOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    private void loadAllRoomIDs() {
        try {
            List<RoomDTO> allRooms = reservationBO.getAllRooms();
            for(RoomDTO r : allRooms){
                cmbRoomID.getItems().add(r.getRoomTypeId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllStudentIDs() {
        try {
            List<StudentDTO> allStudents = reservationBO.getAllStudents();
            for (StudentDTO s : allStudents) {
                cmbStudentID.getItems().add(s.getStudentId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
