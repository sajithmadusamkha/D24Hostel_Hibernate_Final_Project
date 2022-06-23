package lk.D24_Hostel.hostelSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.D24_Hostel.hostelSystem.bo.BOFactory;
import lk.D24_Hostel.hostelSystem.bo.custom.StudentBO;
import lk.D24_Hostel.hostelSystem.dto.StudentDTO;
import lk.D24_Hostel.hostelSystem.view.tdm.StudentTM;

import java.time.LocalDate;
import java.util.List;

public class ManageStudentFormController {
    public TextField txtStId;
    public TextField txtStName;
    public TextField txtStAdd;
    public TextField txtConNo;
    public TextField txtDob;
    public TextField txtGender;
    public JFXButton btnSave;
    public TableView<StudentTM> tblStudents;

    private final StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);

    public void initialize(){
        tblStudents.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("studentId"));
        tblStudents.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudents.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudents.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        tblStudents.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblStudents.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));

        //loadAllStudents();
    }

    private void loadAllStudents(){
        tblStudents.getItems().clear();
        try {
            List<StudentDTO> allStudents = studentBO.getAllStudents();
            for(StudentDTO s : allStudents) {
                tblStudents.getItems().add(new StudentTM(s.getStudentId(),s.getName(),s.getAddress(),s.getContactNo(),s.getDob(),s.getGender()));
            }
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
        }
    }

    public void clearFields(){
        txtStId.setText(null);
        txtStName.setText(null);
        txtStAdd.setText(null);
        txtConNo.setText(null);
        txtDob.setText(null);
        txtGender.setText(null);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtStId.getText();
        String name = txtStName.getText();
        String address = txtStAdd.getText();
        String contact_no = txtConNo.getText();
        String dob = txtDob.getText();
        String gender= txtGender.getText();

        try {
            if(studentBO.save(new StudentDTO(id,name,address,contact_no, LocalDate.parse(dob),gender))){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
                clearFields();
            }
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtStId.getText();

        try{
            if(studentBO.delete(id)){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted.!").show();
            }
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
        }
    }
}
