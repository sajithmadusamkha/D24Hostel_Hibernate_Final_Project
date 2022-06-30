package lk.D24_Hostel.hostelSystem.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.D24_Hostel.hostelSystem.bo.BOFactory;
import lk.D24_Hostel.hostelSystem.bo.custom.UserBO;
import lk.D24_Hostel.hostelSystem.dto.UserDTO;

import java.io.IOException;

public class MainFormController {
    public AnchorPane root;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPwd;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    public void loginOnAction(ActionEvent actionEvent) throws Exception , IOException {
        String userName = txtUserName.getText();
        String password = txtPwd.getText();
        //UserDTO userDTO = userBO.loginValidation(userName,password);
        //if(txtUserName.getText()txtPwd.getText().equals(userBO.loginValidation(userName,password))){}
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/D24_Hostel/hostelSystem/view/DashBoardForm.fxml"));
        Scene subScene = new Scene(parent);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(subScene);
        stage.centerOnScreen();
    }
}
