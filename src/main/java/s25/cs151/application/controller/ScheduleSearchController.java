package s25.cs151.application.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import s25.cs151.application.CommonObjects;
import s25.cs151.application.Main;
import s25.cs151.application.Schedule;

import java.io.IOException;
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

    @FXML
    public void setUpColumns(String name) {
        CommonObjects commonObject = CommonObjects.getInstance();
        ObservableList<Schedule> scheduleCSVList = commonObject.getScheduleCSVList();

        //filtering the schedule by name
        if (name != null) {
            List<Schedule> filteredList = scheduleCSVList.stream().filter(schedule -> name.equals(schedule.getStudentName())).toList();
            scheduleCSVList.setAll(filteredList);
        }

        // Set up columns
        scheduleDate.setCellValueFactory(new PropertyValueFactory<>("scheduleDate"));
        timeSlot.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
        timeSlot.setStyle("-fx-font-family: monospace;-fx-font-weight: bold;");
        courseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        courseName.setStyle("-fx-font-family: monospace;-fx-font-weight: bold;");
        studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        reason.setCellValueFactory(new PropertyValueFactory<>("reason"));
        comment.setCellValueFactory(new PropertyValueFactory<>("comment"));

        //dynamically changing the prompt if no schedules are found
        if(scheduleCSVList.isEmpty()) {
            table.setPlaceholder(new javafx.scene.control.Label("No schedules found " + (name == null ? "" : "for " + name)));
        }
        table.setItems(scheduleCSVList);
    }

    @FXML
    public void initialize() {
        setUpColumns("");
    }

    @FXML
    void searchName() {
        setUpColumns(searchField.getText());
        searchField.clear();
    }

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
