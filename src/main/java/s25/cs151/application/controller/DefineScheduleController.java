package s25.cs151.application.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import s25.cs151.application.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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
                courseNameList.add(course.getCourseCode().replace("-", "") + "-0" + course.getCourseNumber());
            }
            courseBox.setItems(courseNameList);
            courseBox.setValue(courseCSVList.get(0).getCourseCode().replace("-", "")+"-0"+courseCSVList.get(0).getCourseNumber());
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
            timeSlotBox.setValue(timeSlotCSVList.get(0).getFromHour() + " - " + timeSlotCSVList.get(0).getToHour());
        }
    }

    @FXML
    public void onSubmitClick(ActionEvent actionEvent) {

        if(nameField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Schedule");
            alert.setHeaderText(null);
            alert.setContentText("Please enter in all required fields (Name, Date, Time Slot, Course)");
            alert.showAndWait();
            return;
        }

        String studentName = nameField.getText();
        LocalDate date = datePicker.getValue(); // Returns a LocalDate Object
        String timeSlot = (String) timeSlotBox.getValue();
        String course = (String) courseBox.getValue();
        String reason = reasonTextArea.getText();
        String comment = commentTextArea.getText();

        if(reasonTextArea.getText().isEmpty()) {
            reason = "None.";
        }

        if(commentTextArea.getText().isEmpty()) {
            comment = "None.";
        }

        System.out.println(studentName);
        System.out.println(date);
        System.out.println(timeSlot);
        System.out.println(course);
        System.out.println(reason);
        System.out.println(comment);

        try {
            File file = new File("schedule.csv");
            FileWriter fw = new FileWriter(file, true);
            fw.append(studentName + "," + date + "," + timeSlot + "," + course
                        + "," + reason + "," + comment + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home-page.fxml"));
            Pane pane = fxmlLoader.load();

            schedulePane.getChildren().clear();
            schedulePane.getChildren().add(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
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
