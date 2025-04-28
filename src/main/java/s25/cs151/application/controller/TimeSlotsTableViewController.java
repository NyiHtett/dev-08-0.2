package s25.cs151.application.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import s25.cs151.application.CommonObjects;
import s25.cs151.application.Main;
import s25.cs151.application.TimeSlot;

import java.io.File;
import java.io.IOException;

public class TimeSlotsTableViewController {
    @FXML
    private AnchorPane timeSlotsPane;

    @FXML
    private TableView<TimeSlot> table;

    @FXML
    private TableColumn<TimeSlot, String> fromHour;

    @FXML
    private TableColumn<TimeSlot, String> toHour;

    @FXML
    public void initialize() {

        CommonObjects commonObject = CommonObjects.getInstance();
        // ObservableList<TimeSlot> timeSlotCSVList = commonObject.getTimeSlotCSVList();

        File file = new File("src/csv_files/time_slots.csv");
        ObservableList<TimeSlot> timeSlotCSVList = commonObject.getCSVList(file);

        // Set up columns
        fromHour.setCellValueFactory(new PropertyValueFactory<>("fromHour"));
        toHour.setCellValueFactory(new PropertyValueFactory<>("toHour"));

        table.setItems(timeSlotCSVList);
    }

    @FXML
    protected void onExitClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home-page.fxml"));
            Pane pane = fxmlLoader.load();

            timeSlotsPane.getChildren().clear();
            timeSlotsPane.getChildren().add(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
