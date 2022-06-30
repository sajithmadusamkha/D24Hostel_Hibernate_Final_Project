package lk.D24_Hostel.hostelSystem.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.D24_Hostel.hostelSystem.bo.BOFactory;
import lk.D24_Hostel.hostelSystem.bo.custom.UserBO;
import lk.D24_Hostel.hostelSystem.dto.UserDTO;
import lk.D24_Hostel.hostelSystem.entity.User;

import java.io.IOException;

public class MainFormController {
    public AnchorPane root;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPwd;
    public JFXTextField txtShowPassword;
    public JFXCheckBox showPassword;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    public void loginOnAction(ActionEvent actionEvent) throws Exception , IOException {
        String userName = txtUserName.getText();
        String passWord = txtPwd.getText();
        UserDTO user = userBO.loginValidation(userName);

        if(user != null){
            if(passWord.equals(user.getPassword())){
                Parent parent = FXMLLoader.load(getClass().getResource("/lk/D24_Hostel/hostelSystem/view/DashBoardForm.fxml"));
                Scene subScene = new Scene(parent);
                Stage stage = (Stage) this.root.getScene().getWindow();
                stage.setScene(subScene);
                stage.centerOnScreen();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
        }

        /*if(user.getUserName().equalsIgnoreCase(txtUserName.getText())){
            if(user.getPassword().equalsIgnoreCase(txtPwd.getText())){
                Parent parent = FXMLLoader.load(getClass().getResource("/lk/D24_Hostel/hostelSystem/view/DashBoardForm.fxml"));
                Scene subScene = new Scene(parent);
                Stage stage = (Stage) this.root.getScene().getWindow();
                stage.setScene(subScene);
                stage.centerOnScreen();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
        }*/
    }

    public void changeVisibility_OnAction(ActionEvent actionEvent) {
        if(showPassword.isSelected()){
            txtShowPassword.setText(txtPwd.getText());
            txtShowPassword.setVisible(true);
            txtPwd.setVisible(false);
            return;
        }
        txtShowPassword.setText(txtPwd.getText());
        txtShowPassword.setVisible(false);
        txtPwd.setVisible(true);
    }
}
