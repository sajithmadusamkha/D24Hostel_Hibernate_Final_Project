package lk.D24_Hostel.hostelSystem.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.D24_Hostel.hostelSystem.bo.BOFactory;
import lk.D24_Hostel.hostelSystem.bo.custom.ReservationBO;
import lk.D24_Hostel.hostelSystem.dto.ReservationDTO;
import lk.D24_Hostel.hostelSystem.dto.RoomDTO;
import lk.D24_Hostel.hostelSystem.dto.StudentDTO;
import lk.D24_Hostel.hostelSystem.entity.Room;
import lk.D24_Hostel.hostelSystem.entity.Student;
import lk.D24_Hostel.hostelSystem.view.tdm.ReservationTM;

import java.time.LocalDate;
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
    public TableView<ReservationTM> tblReservation;
    public Label lblDate;

    private final ReservationBO reservationBO = (ReservationBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RESERVATION);

    public void initialize(){
        tblReservation.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("roomId"));
        tblReservation.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("roomType"));
        tblReservation.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("studentQry"));
        tblReservation.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        tblReservation.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));
        tblReservation.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("status"));
        TableColumn<ReservationTM, Button> lastCol = (TableColumn<ReservationTM, Button>) tblReservation.getColumns().get(6);
        lastCol.setCellValueFactory(param -> {
            Button btnDelete = new Button("Delete");
            btnDelete.setOnAction(event -> {
                tblReservation.getItems().remove(param.getValue());
                tblReservation.getSelectionModel().clearSelection();
            });
            return new ReadOnlyObjectWrapper<>(btnDelete);
        });

        lblDate.setText(LocalDate.now().toString());

        cmbStudentID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){

            }
        });

        loadAllStudentIDs();
        loadAllRoomIDs();
    }

    public void btnReserveOnAction(ActionEvent actionEvent) {
        String id = txtReserveId.getText();
        String date = lblDate.getText();
        Student studentId = (Student) cmbStudentID.getSelectionModel().getSelectedItem();
        Room roomId = (Room) cmbRoomID.getSelectionModel().getSelectedItem();
        String keyMoney = txtKeyMoney.getText();
        String status = txtStatus.getText();
        String qty = txtStatus.getText();

        try{
            if(reservationBO.saveReservation(new ReservationDTO(id,LocalDate.parse(date),studentId,roomId,Double.parseDouble(keyMoney),status,Integer.parseInt(qty)))){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
            }
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
        }
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
