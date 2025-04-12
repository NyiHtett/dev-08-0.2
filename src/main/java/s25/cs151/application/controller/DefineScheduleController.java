package s25.cs151.application.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import s25.cs151.application.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

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
        /*Date Picker*/
        // Sets datePicker's value to the current date
        datePicker.setValue(LocalDate.now());

        /*Course Dropdown Menu*/
        // Assigning the one and only CommonObjects reference to commonObjects
        CommonObjects commonObjects = CommonObjects.getInstance();
        // courseCSVList now contains a list of Course Objects sorted by professors preference
        ObservableList<Course> courseCSVList = commonObjects.getCourseCSVList();
        // If not empty, we set courseBox's default value and dropdown values
        if (!courseCSVList.isEmpty()) {
            // Creating a list of the Course Object's names to be displayed by the dropdown
            ObservableList<String> courseNameList = FXCollections.observableArrayList();
            for (Course course: courseCSVList) {
                courseNameList.add(course.getCourseName());
            }
            courseBox.setItems(courseNameList);
            courseBox.setValue(courseCSVList.get(0).getCourseName());
        }

        /*Time Slot Dropdown Menu*/
        ObservableList<TimeSlot> timeSlotCSVList = commonObjects.getTimeSlotCSVList();
        if (!timeSlotCSVList.isEmpty()) {
            ObservableList<String> timeSlotNewFormatList = FXCollections.observableArrayList();
            String format = "";
            for (TimeSlot timeSlot: timeSlotCSVList) {
                format = timeSlot.getFromHour() + " - " + timeSlot.getToHour();
                timeSlotNewFormatList.add(format);
            }
            timeSlotBox.setItems(timeSlotNewFormatList);
            timeSlotBox.setValue(timeSlotCSVList.get(0).getFromHour() + "  - " + timeSlotCSVList.get(0).getToHour());
        }
    }

    @FXML
    public void onSubmitClick(ActionEvent actionEvent) {
        String studentName = nameField.getText();
        LocalDate date = datePicker.getValue(); // Returns a LocalDate Object
        String timeSlot = (String) timeSlotBox.getValue();
        String course = (String) courseBox.getValue();
        String reason = reasonTextArea.getText();
        String comment = commentTextArea.getText();

        System.out.println(studentName);
        System.out.println(date);
        System.out.println(timeSlot);
        System.out.println(course);
        System.out.println(reason);
        System.out.println(comment);

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
