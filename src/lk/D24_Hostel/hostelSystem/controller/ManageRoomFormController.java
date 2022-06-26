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

        try{
            if(roomBO.save(new RoomDTO(id,type,Double.parseDouble(keyMoney),Integer.parseInt(qty)))){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
                clearText();
            }
        } catch (Exception e){
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String RoomId = txtRoomID.getText();

        try{
            if(roomBO.delete(RoomId)){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted.!").show();
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
