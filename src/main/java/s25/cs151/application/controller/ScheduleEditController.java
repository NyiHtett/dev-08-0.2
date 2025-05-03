package s25.cs151.application.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import s25.cs151.application.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Collections;

public class ScheduleEditController {
    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox timeSlotBox;

    @FXML
    private ComboBox courseBox;

    @FXML
    private AnchorPane viewSchedulePane;

    @FXML
    private AnchorPane editingPane;

    @FXML
    private TableView table;

    @FXML
    private TableColumn<Schedule, String> scheduleDate;

    @FXML
    private TableColumn timeSlot;

    @FXML
    private TableColumn courseName;

    @FXML
    private TableColumn studentName;

    @FXML
    private TableColumn reason;

    @FXML
    private TableColumn comment;

    @FXML
    private TextField searchField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField reasonField;

    @FXML
    private TextField commentField;

    private CommonObjects commonObject = CommonObjects.getInstance();
    // private ObservableList<Schedule> scheduleCSVList = commonObject.getScheduleCSVList();
    File file = new File("src/csv_files/schedule.csv");
    private ObservableList<Schedule> scheduleCSVList = commonObject.getCSVList(file);

    // List to contain the searched objects
    private ObservableList<Schedule> searchList = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        editingPane.setVisible(false);
        editingPane.setAccessibleText("Please choose one schedule to edit");
    }

    /**
     * Purpose: Display the contents of list.
     * @param list The ObservableList to display.
     */
    @FXML
    public void setUpColumns(ObservableList<Schedule> list) {
        // Set up columns

        scheduleDate.setCellValueFactory(new PropertyValueFactory<>("scheduleDate"));
        timeSlot.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
        timeSlot.setStyle("-fx-font-family: monospace;-fx-font-weight: bold;");
        courseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        courseName.setStyle("-fx-font-family: monospace;-fx-font-weight: bold;");
        studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        reason.setCellValueFactory(new PropertyValueFactory<>("reason"));
        comment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        table.setItems(list);
    }

    /**
     * Purpose: Searches schedule appointment by name and invokes setUpColumns().
     */
    @FXML
    void searchName() {
        searchList.clear();

        // Creating subset of "scheduleCSVList" called "searchList" by name.
        // Student name searched
        String searchedName = searchField.getText();
        if (searchedName != null) {
            for (Schedule schedule : scheduleCSVList) {
                if (schedule.getStudentName().toLowerCase().contains(searchedName.toLowerCase())) {
                    searchList.add(schedule);
                }
            }
        }

        //dynamically changing the prompt if no schedules are found
        if (searchList.isEmpty()) {
            table.setPlaceholder(new javafx.scene.control.Label("No schedules found " + (searchedName == null ? "" : "for " + searchedName)));
        }

        Collections.reverse(searchList);

        // Displays the subset (search/filtered) list
        setUpColumns(searchList);
    }

    @FXML
    public void editSchedule(ActionEvent actionEvent) {
        Object o = table.getSelectionModel().getSelectedItem();
        Schedule selectedSchedule = (Schedule) o;

        if (o == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a schedule from the table to edit.");
            alert.showAndWait();
            return;
        }

        // Terminal output
        System.out.println("Printing schedule to edit:");
        System.out.println(selectedSchedule);

        editingPane.setVisible(true);

        // Omar's Portion
        // Setting date pickers dropdown default value
        datePicker.setValue(LocalDate.now());

        // Setting courses dropdown options
        File file1 = new File("src/csv_files/courses.csv");
        ObservableList<Course> courseCSVList = commonObject.getCSVList(file1);

        // If not empty, we set courseBox's default value and dropdown values
        if (!courseCSVList.isEmpty()) {
            // Creating a list of the Course Object's names to be displayed by the dropdown
            ObservableList<String> courseNameList = FXCollections.observableArrayList();
            for (Course course: courseCSVList) {
                courseNameList.add(course.getCourseCode().replace("-", "") + "-0" + course.getCourseNumber());
            }
            courseBox.setItems(courseNameList);
            courseBox.setValue(courseCSVList.get(0).getCourseCode().replace("-", "")+"-0"+courseCSVList.get(0).getCourseNumber());
            courseBox.setStyle("-fx-font-family: monospace;-fx-font-weight: bold;");
        }

        File file2 = new File("src/csv_files/time_slots.csv");
        ObservableList<TimeSlot> timeSlotCSVList = commonObject.getCSVList(file2);
        if (!timeSlotCSVList.isEmpty()) {
            ObservableList<String> timeSlotNewFormatList = FXCollections.observableArrayList();
            String format = "";
            for (TimeSlot timeSlot: timeSlotCSVList) {
                format = timeSlot.getFromHour() + " - " + timeSlot.getToHour();
                timeSlotNewFormatList.add(format);
            }
            timeSlotBox.setItems(timeSlotNewFormatList);
            timeSlotBox.setValue(timeSlotCSVList.get(0).getFromHour() + " - " + timeSlotCSVList.get(0).getToHour());
            timeSlotBox.setStyle("-fx-font-family: monospace;-fx-font-weight: bold;");
        }

        // Setting the default value of the text fields
        nameField.setText(selectedSchedule.getStudentName()); // edited
        reasonField.setText(selectedSchedule.getReason());
        commentField.setText(selectedSchedule.getComment());

    }

    /**
     * Purpose: Delete the selected object/schedule appointment.
     * @param actionEvent
     */
    public void delete(ActionEvent actionEvent) {
        // Save selected schedule
        Object o = table.getSelectionModel().getSelectedItem();
        Schedule selectedSchedule = (Schedule) o;

        if (o == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a schedule from the table to delete.");
            alert.showAndWait();
            return;
        }

        // Terminal output
        System.out.println("Printing schedule to delete:");
        System.out.println(selectedSchedule);

        // Removes from both primary and helper subset list
        scheduleCSVList.remove(selectedSchedule);
        searchList.remove(selectedSchedule);

        // Console output
        System.out.println("Printing scheduleCSVList objects:");
        for (Schedule schedule: scheduleCSVList) {
            System.out.println(schedule);
        }

        // Updates the contents of schedule.csv to exclude the deleted appointment
        updateScheduleCSVFile();
        // Resets the searchList
        searchList.clear();
        // Restarts the search using the previously given name to display an
        // updated version of searchList.
        searchName();
    }

    /**
     * Purpose: Update/rewrites the CSV to exclude deleted content.
     */
    public void updateScheduleCSVFile() {
        try {
            File file = new File("src/csv_files/schedule.csv");
            PrintWriter pw = new PrintWriter(file);
            pw.println("StudentName,ScheduleDate,TimeSlot,Course,Reason,Comment");
            for (Schedule schedule: scheduleCSVList) {
                pw.println(schedule);
            }
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Purpose: Redirect user back to the home page.
     * @param actionEvent
     */
    @FXML
    public void onExitClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home-page.fxml"));
            Pane pane = fxmlLoader.load();

            viewSchedulePane.getChildren().clear();
            viewSchedulePane.getChildren().add(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void saveClick(ActionEvent actionEvent) {
        // Save selected schedule
        Object o = table.getSelectionModel().getSelectedItem();
        Schedule selectedSchedule = (Schedule) o;

        // Terminal output
        System.out.println("Printing schedule to edit:");
        System.out.println(selectedSchedule);

        // Removes from both primary and helper subset list
        scheduleCSVList.remove(selectedSchedule);
        searchList.remove(selectedSchedule);

        // Adding the edited schedule
        String studentName = nameField.getText();
        LocalDate date = datePicker.getValue(); // Returns a LocalDate Object
        String timeSlot = (String) timeSlotBox.getValue();
        String course = (String) courseBox.getValue();
        String reason = reasonField.getText();
        String comment = commentField.getText();

        // Ensures a name is provided
        if (studentName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Provide a student name.");
            alert.showAndWait();
            return;
        }

        // Ensures no cell entry is left empty
        if (reason.isEmpty()) {
            reason = "None.";
        }
        if (comment.isEmpty()) {
            comment = "None";
        }
        Schedule editedSchedule = new Schedule(studentName, date + "", timeSlot,
                course, reason, comment);

        if(!scheduleCSVList.contains(editedSchedule)) {
            scheduleCSVList.add(editedSchedule);
        }
        // Updates the contents of schedule.csv to exclude the deleted appointment
        updateScheduleCSVFile();
        // Resets the searchList
        searchList.clear();
        // Restarts the search using the previously given name to display an
        // updated version of searchList.
        searchName();
        editingPane.setVisible(false);
    }
    
}
