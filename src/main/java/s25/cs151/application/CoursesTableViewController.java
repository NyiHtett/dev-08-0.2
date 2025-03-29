package s25.cs151.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

public class CoursesTableViewController {
    @FXML
    private AnchorPane coursesPane;

    @FXML
    private TableView<Courses> table;

    @FXML
    private TableColumn<Courses, String> courseCode;

    @FXML
    private TableColumn<Courses, String> courseName;

    @FXML
    private TableColumn<Courses, Integer> sectionNumber;

    public static class Courses implements Comparable<Courses> {
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
        public int compareTo(Courses other) {
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
    public void initialize() {
        try {
            File file = new File("courses.csv");
            Scanner sc = new Scanner(file);

            String line = "";
            String[] elements;
            ObservableList<Courses> data = FXCollections.observableArrayList();

            sc.nextLine();
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                elements = line.split(",");

                Courses course = new Courses(elements[0], elements[1], Integer.parseInt(elements[2]));
                data.add(course);
            }

            // Set up columns
            courseCode.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
            courseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
            sectionNumber.setCellValueFactory(new PropertyValueFactory<>("courseNumber"));

            Collections.sort(data);
            table.setItems(data);
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
