package s25.cs151.application.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import s25.cs151.application.CommonObjects;
import s25.cs151.application.Main;
import s25.cs151.application.Schedule;

import java.io.File;
import java.io.IOException;

public class ScheduleViewController {
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
    public void initialize() {

        CommonObjects commonObject = CommonObjects.getInstance();
        // ObservableList<Schedule> scheduleCSVList = commonObject.getScheduleCSVList();

        File file = new File("src/csv_files/schedule.csv");
        ObservableList<Schedule> scheduleCSVList = commonObject.getCSVList(file);

        // Set up columns
        scheduleDate.setCellValueFactory(new PropertyValueFactory<>("scheduleDate"));
        timeSlot.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
        timeSlot.setStyle("-fx-font-family: monospace;-fx-font-weight: bold;");;
        courseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        courseName.setStyle("-fx-font-family: monospace;-fx-font-weight: bold;");
        studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        reason.setCellValueFactory(new PropertyValueFactory<>("reason"));
        comment.setCellValueFactory(new PropertyValueFactory<>("comment"));

        table.setItems(scheduleCSVList);
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
