package lk.D24_Hostel.hostelSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormController {
    public AnchorPane root;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/D24_Hostel/hostelSystem/view/DashBoardForm.fxml"));
        Scene subScene = new Scene(parent);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(subScene);
        stage.centerOnScreen();
    }
}
