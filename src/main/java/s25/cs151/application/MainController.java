package s25.cs151.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

import javafx.scene.layout.Pane;


public class MainController {

    @FXML
    Pane mainPane;

    // Directs user to define office hours page
    @FXML
    protected void onDefineOfficeHoursClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("define-office-hours.fxml"));
            Pane pane = fxmlLoader.load();

            mainPane.getChildren().clear();
            mainPane.getChildren().add(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Directs user to view office hours page
    @FXML
    protected void onViewOfficeHoursClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view-office-hours.fxml"));
            Pane pane = fxmlLoader.load();

            mainPane.getChildren().clear();
            mainPane.getChildren().add(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}