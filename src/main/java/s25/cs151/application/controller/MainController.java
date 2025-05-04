package s25.cs151.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import javafx.scene.layout.Pane;
import s25.cs151.application.Main;


public class MainController {

    @FXML
    Pane mainPane;

    // Directs user to define office hours page
    @FXML
    protected void onDefineOfficeHoursClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/define-office-hours.fxml"));
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
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/view-office-hours.fxml"));
            Pane pane = fxmlLoader.load();

            mainPane.getChildren().clear();
            mainPane.getChildren().add(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onDefineTimeSlotsClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/define-time-slots.fxml"));
            Pane pane = fxmlLoader.load();

            mainPane.getChildren().clear();
            mainPane.getChildren().add(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onViewTimeSlotsClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/view-semester-time-slots.fxml"));
            Pane pane = fxmlLoader.load();

            mainPane.getChildren().clear();
            mainPane.getChildren().add(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onDefineCoursesClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/course.fxml"));
            Pane pane = fxmlLoader.load();

            mainPane.getChildren().clear();
            mainPane.getChildren().add(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onViewCoursesClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/view-courses.fxml"));
            Pane pane = fxmlLoader.load();

            mainPane.getChildren().clear();
            mainPane.getChildren().add(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onDefineScheduleClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/define-schedule.fxml"));
            Pane pane = fxmlLoader.load();

            mainPane.getChildren().clear();
            mainPane.getChildren().add(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onViewScheduleClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/view-schedule.fxml"));
            Pane pane = fxmlLoader.load();

            mainPane.getChildren().clear();
            mainPane.getChildren().add(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onSearchScheduleClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/search-schedule.fxml"));
            Pane pane = fxmlLoader.load();

            mainPane.getChildren().clear();
            mainPane.getChildren().add(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onSearchEditClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/edit-schedule.fxml"));
            Pane pane = fxmlLoader.load();

            mainPane.getChildren().clear();
            mainPane.getChildren().add(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
