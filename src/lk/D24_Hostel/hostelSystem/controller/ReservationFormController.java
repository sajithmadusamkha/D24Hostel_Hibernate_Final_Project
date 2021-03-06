package lk.D24_Hostel.hostelSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.D24_Hostel.hostelSystem.bo.BOFactory;
import lk.D24_Hostel.hostelSystem.bo.custom.ReservationBO;
import lk.D24_Hostel.hostelSystem.bo.custom.RoomBO;
import lk.D24_Hostel.hostelSystem.dto.ReservationDTO;
import lk.D24_Hostel.hostelSystem.dto.RoomDTO;
import lk.D24_Hostel.hostelSystem.dto.StudentDTO;
import lk.D24_Hostel.hostelSystem.entity.Room;
import lk.D24_Hostel.hostelSystem.entity.Student;
import lk.D24_Hostel.hostelSystem.view.tdm.ReservationTM;

import java.time.LocalDate;
import java.util.List;

public class ReservationFormController {
    public ComboBox<Object> cmbStudentID;
    public ComboBox<Object> cmbRoomID;
    public TextField txtReserveId;
    public TextField txtStudentName;
    public TextField txtRoomType;
    public TextField txtKeyMoney;
    public TextField txtRoomQty;
    public TextField txtStatus;
    public TextField txtStudentQty;
    public TableView<ReservationTM> tblReservation;
    public Label lblDate;
    public JFXButton btnSave;
    public Label lblRoomId;
    public Label lblQty;


    private final ReservationBO reservationBO = (ReservationBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RESERVATION);
    private final RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);

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
                try {
                    StudentDTO search = reservationBO.searchStudent(newValue + "");
                    txtStudentName.setText(search.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e);
                }
            } else {
                txtStudentName.clear();
            }
        });

        cmbRoomID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                try {
                    RoomDTO search = reservationBO.searchRoom(newValue + "");
                    txtRoomType.setText(search.getType());
                    txtKeyMoney.setText(search.getKeyMoney() + "");
                    txtRoomQty.setText(search.getRoomQty() + "");
                    lblRoomId.setText(search.getRoomTypeId());
                    lblQty.setText(search.getRoomQty() + "");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e);
                }
            } else {
                txtRoomQty.clear();
                txtKeyMoney.clear();
                txtRoomType.clear();
                txtStatus.clear();
                txtStudentQty.clear();
            }
        });

        tblReservation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedReservation) -> {
            if(selectedReservation != null) {
                cmbRoomID.setDisable(false);
                cmbRoomID.setValue(selectedReservation.getRoomId());
                btnSave.setText("Update");
                txtRoomQty.setText(Integer.parseInt(txtRoomQty.getText()) + "");
                txtStudentQty.setText(selectedReservation.getStudentQry() + "");
            } else {
                btnSave.setText("Add");
                cmbRoomID.setDisable(false);
                cmbRoomID.getSelectionModel().clearSelection();
                txtStudentQty.clear();
            }
        });

        loadAllStudentIDs();
        loadAllRoomIDs();
        initUi();
    }

    private void initUi() {
        txtStudentName.setEditable(false);
        txtRoomType.setEditable(false);
        txtRoomQty.setEditable(false);
        txtKeyMoney.setEditable(false);
    }

    public void btnReserveOnAction(ActionEvent actionEvent) throws Exception {
        String id = txtReserveId.getText();
        String date = lblDate.getText();
        String keyMoney = txtKeyMoney.getText();
        String status = txtStatus.getText();
        String qty = txtStudentQty.getText();

        //Student
        StudentDTO studentDTO = reservationBO.searchStudent((String) cmbStudentID.getValue());
        Student student = new Student(studentDTO.getStudentId(),studentDTO.getName(),studentDTO.getAddress(),studentDTO.getContactNo(),studentDTO.getDob(),studentDTO.getGender());

        //Room
        RoomDTO roomDTO = reservationBO.searchRoom((String) cmbRoomID.getValue());
        Room room = new Room(roomDTO.getRoomTypeId(),roomDTO.getType(),roomDTO.getKeyMoney(),roomDTO.getRoomQty());

        try{
            if(reservationBO.saveReservation(new ReservationDTO(id,LocalDate.parse(date),student,room,Double.parseDouble(keyMoney),status,Integer.parseInt(qty)))){
                updateRoomQty((String) cmbRoomID.getValue());
                new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
            }
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
        }
    }

    private void updateRoomQty(String id) throws Exception {
        RoomDTO roomDTO = reservationBO.searchRoom(id);
        Integer newQty = roomDTO.getRoomQty() - Integer.parseInt(txtStudentQty.getText());
        roomDTO.setRoomQty(newQty);
        roomBO.update(roomDTO);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String id = (String) cmbRoomID.getSelectionModel().getSelectedItem();
        double keyMoney = Double.parseDouble(txtKeyMoney.getText());
        String status = txtStatus.getText();
        Integer qty = Integer.parseInt(txtStudentQty.getText());
        String roomType = txtRoomType.getText();
        double total = keyMoney*qty;

        boolean exists = tblReservation.getItems().stream().anyMatch(reserve -> reserve.getRoomId().equals(id));

        if(exists) {
            ReservationTM reservationTM = tblReservation.getItems().stream().filter(reserve -> reserve.getRoomId().equals(id)).findFirst().get();

            if(btnSave.getText().equalsIgnoreCase("Update")){
                reservationTM.setStudentQry(qty);
                reservationTM.setTotal(total);
                tblReservation.getSelectionModel().clearSelection();
            } else {
                reservationTM.setStudentQry(reservationTM.getStudentQry() + qty);
                total = reservationTM.getStudentQry()*qty;
                reservationTM.setTotal(total);
            }
            tblReservation.refresh();
        } else {
            tblReservation.getItems().add(new ReservationTM(id,roomType,qty,keyMoney,total,status));
        }
        cmbRoomID.requestFocus();
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
