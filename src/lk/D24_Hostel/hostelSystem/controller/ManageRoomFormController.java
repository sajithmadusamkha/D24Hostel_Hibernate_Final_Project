package lk.D24_Hostel.hostelSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.D24_Hostel.hostelSystem.bo.BOFactory;
import lk.D24_Hostel.hostelSystem.bo.custom.RoomBO;
import lk.D24_Hostel.hostelSystem.dto.RoomDTO;
import lk.D24_Hostel.hostelSystem.view.tdm.RoomTM;

import java.util.List;

public class ManageRoomFormController {
    public TextField txtRoomID;
    public TextField txtRoomType;
    public TextField txtKeyMoney;
    public TextField txtRoomQty;
    public JFXButton btnSave;
    public TableView<RoomTM> tblRooms;

    private final RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);

    public void initialize(){
        tblRooms.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        tblRooms.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblRooms.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        tblRooms.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("roomQty"));

        tblRooms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnSave.setText(newValue != null ? "Update" : "Save");

            if(newValue != null){
                txtRoomID.setText(newValue.getRoomTypeId());
                txtRoomType.setText(newValue.getType());
                txtRoomQty.setText(String.valueOf(newValue.getRoomQty()));
                txtKeyMoney.setText(String.valueOf(newValue.getKeyMoney()));
            }
        });

        loadAllRooms();
    }

    private void loadAllRooms() {
        tblRooms.getItems().clear();

        try{
            List<RoomDTO> rooms = roomBO.getAllRooms();
            for (RoomDTO r : rooms) {
                tblRooms.getItems().add(new RoomTM(r.getRoomTypeId(),r.getType(),r.getKeyMoney(),r.getRoomQty()));
            }
        } catch (Exception e){
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
        }
    }

    public void btnSavoOnAction(ActionEvent actionEvent) {
        String id = txtRoomID.getText();
        String type = txtRoomType.getText();
        String keyMoney = txtKeyMoney.getText();
        String qty = txtRoomQty.getText();

        if(btnSave.getText().equalsIgnoreCase("Save")){
            try{
                if(roomBO.save(new RoomDTO(id,type,Double.parseDouble(keyMoney),Integer.parseInt(qty)))){
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
                    tblRooms.getItems().add(new RoomTM(id,type,Double.parseDouble(keyMoney),Integer.parseInt(qty)));
                    clearText();
                }
            } catch (Exception e){
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
            }
        } else {
            try{
                if(roomBO.update(new RoomDTO(id,type,Double.parseDouble(keyMoney),Integer.parseInt(qty)))){
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated.!").show();
                    loadAllRooms();
                    clearText();
                }
            } catch (Exception e) {
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
            }
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String RoomId = txtRoomID.getText();

        try{
            if(roomBO.delete(RoomId)){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted.!").show();
                loadAllRooms();
                clearText();
            }
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
        }
    }

    private void clearText(){
        txtRoomID.setText(null);
        txtRoomType.setText(null);
        txtKeyMoney.setText(null);
        txtRoomQty.setText(null);
    }
}
