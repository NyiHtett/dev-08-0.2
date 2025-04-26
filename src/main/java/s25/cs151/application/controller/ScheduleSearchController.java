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
import java.util.Collections;

public class ScheduleSearchController {
    @FXML
    private AnchorPane viewSchedulePane;

    @FXML
    private TableView table;

    @FXML
    private TableColumn scheduleDate;

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

    private CommonObjects commonObject = CommonObjects.getInstance();
    private ObservableList<Schedule> scheduleCSVList = commonObject.getScheduleCSVList();
    // List to contain the searched objects
    private ObservableList<Schedule> searchList = FXCollections.observableArrayList();

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

}
