package lk.D24_Hostel.hostelSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {
    public AnchorPane root;
    public AnchorPane mainRoot;

    public void initialize(){
        try {
            loadUi("ManageStudentForm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mngStudentOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("ManageStudentForm");
    }

    public void mngRoomOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("ManageRoomForm");
    }

    public void rsvOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("ReservationForm");
    }

    public void mngUserOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("ManageUserForm");
    }


    public void btnRemainKeyMny(ActionEvent actionEvent) throws IOException {
        loadUi("RemainKeyMoneyForm");
    }

    public void logOut(MouseEvent mouseEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/D24_Hostel/hostelSystem/view/MainForm.fxml"));
        Scene subScene = new Scene(parent);
        Stage stage = (Stage) this.mainRoot.getScene().getWindow();
        stage.setScene(subScene);
        stage.centerOnScreen();
    }

    private void loadUi(String ui) throws IOException {
        root.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/D24_Hostel/hostelSystem/view/"+ui+".fxml"));
        root.getChildren().add(parent);
    }
}
