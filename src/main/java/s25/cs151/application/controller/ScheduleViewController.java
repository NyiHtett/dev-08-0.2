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
    public void initialize() {

        CommonObjects commonObject = CommonObjects.getInstance();
        ObservableList<Schedule> scheduleCSVList = commonObject.getScheduleCSVList();

        // Set up columns
        scheduleDate.setCellValueFactory(new PropertyValueFactory<>("scheduleDate"));
        timeSlot.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));

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
