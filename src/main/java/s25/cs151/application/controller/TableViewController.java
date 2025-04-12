package s25.cs151.application.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import s25.cs151.application.CommonObjects;
import s25.cs151.application.Main;
import s25.cs151.application.TermWeekday;

import java.io.IOException;

public class TableViewController {
    public AnchorPane officeHourPane;

    @FXML
    private TableView<TermWeekday> table;

    @FXML
    private TableColumn<TermWeekday, String> semester;

    @FXML
    private TableColumn<TermWeekday, Integer> year;

    @FXML
    private TableColumn<TermWeekday, String> days;

    @FXML
    public void initialize() {

        CommonObjects commonObject = CommonObjects.getInstance();
        ObservableList<TermWeekday> termWeekdayCSVList = commonObject.getTermWeekdayCSVList();

        // Set up columns
        semester.setCellValueFactory(new PropertyValueFactory<>("semester"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        days.setCellValueFactory(new PropertyValueFactory<>("weekdays"));

        table.setItems(termWeekdayCSVList);
    }

    @FXML
    protected void onExitClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home-page.fxml"));
            Pane pane = fxmlLoader.load();

            officeHourPane.getChildren().clear();
            officeHourPane.getChildren().add(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
