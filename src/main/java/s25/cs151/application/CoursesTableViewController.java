package s25.cs151.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class CoursesTableViewController {
    @FXML
    private AnchorPane coursesPane;

    @FXML
    private TableView<CoursesTableViewController.Courses> table;

    @FXML
    private TableColumn<CoursesTableViewController.Courses, String> courseCode;

    @FXML
    private TableColumn<CoursesTableViewController.Courses, String> courseName;

    @FXML
    private TableColumn<CoursesTableViewController.Courses, Integer> courseNumber;

    public static class Courses implements Comparable<CoursesTableViewController.Courses> {
        private String courseCode;
        private String courseName;
        private int courseNumber;

        public Courses(String courseCode, String courseName, int courseNumber) {
            this.courseCode = courseCode;
            this.courseName = courseName;
            this.courseNumber = courseNumber;
        }

        public String getCourseCode() {
            return this.courseCode;
        }

        public String getCourseName() {
            return this.courseName;
        }

        public int getCourseNumber() {
            return this.courseNumber;
        }

        @Override
        public int compareTo(CoursesTableViewController.Courses other) {
            if (this.courseCode.compareTo(other.courseCode) < 0) {
                return 1;
            } else if (this.courseCode.compareTo(other.courseCode) > 0) {
                return -1;
            } else {
                return 0;
            }
        }

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
