package s25.cs151.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class DefineScheduleController {

    @FXML
    public AnchorPane schedulePane;

    @FXML
    public TextField nameField;

    @FXML
    public DatePicker datePicker;

    @FXML
    public ComboBox timeSlotBox;

    @FXML
    public TextArea reasonTextArea;

    @FXML
    public TextArea commentTextArea;

    @FXML
    public void onSubmitClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onCancelClick(ActionEvent actionEvent) {
    }
}
