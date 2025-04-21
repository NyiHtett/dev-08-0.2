package s25.cs151.application.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import s25.cs151.application.CommonObjects;
import s25.cs151.application.Main;
import s25.cs151.application.Schedule;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ScheduleSearchController {
    @FXML
    public AnchorPane viewSchedulePane;

    @FXML
    public TableView table;

    @FXML
    public TableColumn scheduleDate;

    @FXML
    public TableColumn timeSlot;

    @FXML
    public TableColumn courseName;

    @FXML
    public TableColumn studentName;

    @FXML
    public TableColumn reason;

    @FXML
    public TableColumn comment;

    @FXML
    private TextField searchField;

    private CommonObjects commonObject = CommonObjects.getInstance();
    private ObservableList<Schedule> scheduleCSVList = commonObject.getScheduleCSVList();
    // List to contain the searched objects
    private ObservableList<Schedule> filteredObsList = FXCollections.observableArrayList();

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
        // filtering the schedule by name
        // Student name searched
        String searchedName = searchField.getText();
        if (searchedName != null) {
            // Temporary list
            //List<Schedule> filteredList = scheduleCSVList.stream().filter(schedule -> searchedName.equalsIgnoreCase(schedule.getStudentName())).toList();
            ObservableList<Schedule> filteredList = FXCollections.observableArrayList();
            for (Schedule schedule: scheduleCSVList) {
                if (searchedName.equalsIgnoreCase(schedule.getStudentName().substring(0, searchedName.length()))) {
                    filteredList.add(schedule);
                }
            }

            // Sets the values of filteredObsList
            filteredObsList.setAll(filteredList);
        }

        //dynamically changing the prompt if no schedules are found
        if(filteredObsList.isEmpty()) {
            table.setPlaceholder(new javafx.scene.control.Label("No schedules found " + (searchedName == null ? "" : "for " + searchedName)));
        }

        setUpColumns(filteredObsList);
    }

    /**
     * Purpose: Delete the selected object/schedule appointment.
     * @param actionEvent
     */
    public void delete(ActionEvent actionEvent) {
        // Save selected schedule
        Object o = table.getSelectionModel().getSelectedItem();
        Schedule selectedSchedule = (Schedule) o;

        // Terminal output
        System.out.println("Printing schedule to delete:");
        System.out.println(selectedSchedule);

        // Removes from both primary and helper list
        scheduleCSVList.remove(selectedSchedule);
        filteredObsList.remove(selectedSchedule);

        System.out.println("Printing scheduleCSVList objects:");
        for (Schedule schedule: scheduleCSVList) {
            System.out.println(schedule);
        }

        updateScheduleCSVFile();
        searchName();
    }

    /**
     * Purpose: Update/rewrites the CSV to exclude deleted content.
     */
    public void updateScheduleCSVFile() {
        try {
            File file = new File("schedule.csv");
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

}
