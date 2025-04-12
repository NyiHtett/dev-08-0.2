package s25.cs151.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import s25.cs151.application.Main;

import java.io.IOException;
import java.time.LocalDate;

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
    public ComboBox courseBox;

    @FXML
    public TextArea reasonTextArea;

    @FXML
    public TextArea commentTextArea;

    @FXML
    public void initialize() {
        datePicker.setValue(LocalDate.now());
    }

    @FXML
    public void onSubmitClick(ActionEvent actionEvent) {
        String studentName = nameField.getText();
        LocalDate date = datePicker.getValue(); // Returns a LocalDate Object
        String reason = reasonTextArea.getText();
        String comment = commentTextArea.getText();

        /*
        To Do:
        Save data to CSV file
         */
    }

    @FXML
    public void onCancelClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home-page.fxml"));
            Pane pane = fxmlLoader.load();

            schedulePane.getChildren().clear();
            schedulePane.getChildren().add(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
