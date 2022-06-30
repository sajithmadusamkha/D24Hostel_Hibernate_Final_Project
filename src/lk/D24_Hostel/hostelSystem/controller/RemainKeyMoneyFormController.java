package lk.D24_Hostel.hostelSystem.controller;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.D24_Hostel.hostelSystem.bo.BOFactory;
import lk.D24_Hostel.hostelSystem.bo.custom.ReservationBO;
import lk.D24_Hostel.hostelSystem.view.tdm.RemainKeyMoneyTM;

public class RemainKeyMoneyFormController {
    public TableView<RemainKeyMoneyTM> tblUnpaid;

    private final ReservationBO reservationBO = (ReservationBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RESERVATION);

    public void initialize() throws Exception {
        tblUnpaid.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("student_Id"));
        tblUnpaid.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblUnpaid.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("status"));

        ObservableList<RemainKeyMoneyTM> remain = reservationBO.getRemainKeyMoneyStudents();
        tblUnpaid.setItems(remain);
    }
}
