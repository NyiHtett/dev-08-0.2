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
import s25.cs151.application.Course;
import s25.cs151.application.Main;

import java.io.IOException;

public class CoursesTableViewController {
    @FXML
    private AnchorPane coursesPane;

    @FXML
    private TableView<Course> table;

    @FXML
    private TableColumn<Course, String> courseCode;

    @FXML
    private TableColumn<Course, String> courseName;

    @FXML
    private TableColumn<Course, Integer> sectionNumber;

    @FXML
    public void initialize() {
        CommonObjects commonObject = CommonObjects.getInstance();
        ObservableList<Course> courseCSVList = commonObject.getCourseCSVList();

        // Set up columns
        courseCode.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        courseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        sectionNumber.setCellValueFactory(new PropertyValueFactory<>("courseNumber"));

        table.setItems(courseCSVList);
    }

    @FXML
    protected void onExitClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home-page.fxml"));
            Pane pane = fxmlLoader.load();

            coursesPane.getChildren().clear();
            coursesPane.getChildren().add(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
