package lk.D24_Hostel.hostelSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.D24_Hostel.hostelSystem.bo.BOFactory;
import lk.D24_Hostel.hostelSystem.bo.custom.UserBO;
import lk.D24_Hostel.hostelSystem.dto.UserDTO;
import lk.D24_Hostel.hostelSystem.view.tdm.UserTM;

import java.util.List;

public class ManageUserFormController {

    public TextField txtUserId;
    public TextField txtUserName;
    public PasswordField txtPassword;
    public JFXButton btnSave;
    public TableView<UserTM> tblUsers;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    public void initialize(){
        tblUsers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("userId"));
        tblUsers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("userName"));
        tblUsers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("password"));

        tblUsers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnSave.setText(newValue != null ? "Update" : "Save");

            if(newValue != null){
                txtUserId.setText(newValue.getUserId());
                txtUserName.setText(newValue.getUserName());
                txtPassword.setText(newValue.getPassword());
            }
        });

        loadAllUsers();
    }

    private void loadAllUsers() {
        tblUsers.getItems().clear();
        try {
            List<UserDTO> allUsers = userBO.getAllUsers();
            for(UserDTO u : allUsers){
                tblUsers.getItems().add(new UserTM(u.getUserId(),u.getUserName(),u.getPassword()));
            }
        }catch (Exception e){
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
        }
    }

    private void clearAllFields(){
        txtUserId.setText(null);
        txtUserName.setText(null);
        txtPassword.setText(null);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtUserId.getText();
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        if(btnSave.getText().equalsIgnoreCase("Save")){
            try{
                if(userBO.saveUser(new UserDTO(id,userName,password))){
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
                    tblUsers.getItems().add(new UserTM(id,userName,password));
                    loadAllUsers();
                    clearAllFields();
                }
            } catch (Exception e) {
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
            }
        } else {
            try {
                if(userBO.updateUser(new UserDTO(id,userName,password))){
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
                    loadAllUsers();
                    clearAllFields();
                }
            } catch (Exception e) {
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
            }
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtUserId.getText();

        try{
            if(userBO.deleteUser(id)){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted.!").show();
                loadAllUsers();
                clearAllFields();
            }
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
        }
    }
}
