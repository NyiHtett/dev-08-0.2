package s25.cs151.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import s25.cs151.application.Main;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

public class TableViewController {
    public AnchorPane officeHourPane;

    @FXML
    private TableView<OfficeHour> tableView;

    @FXML
    private TableColumn<OfficeHour, String> semesterColumn;

    @FXML
    private TableColumn<OfficeHour, Integer> yearColumn;

    @FXML
    private TableColumn<OfficeHour, String> weekdayColumn;

    public static class OfficeHour implements Comparable<OfficeHour> {
        private String semester;
        private int semesterVal;
        private int year;
        private String weekdays;

        public OfficeHour(String semester, int year, String weekdays) {
            this.semester = semester;
            this.year = year;
            this.weekdays = weekdays;
        }

        public String getSemester() {
            return this.semester;
        }

        public int getYear() {
            return this.year;
        }

        public String getWeekdays() {
            return this.weekdays;
        }

        public void setSemesterVal() {
            if (this.semester.equals("Spring")) {
                this.semesterVal = 1;
            } else if (this.semester.equals("Summer")) {
                this.semesterVal = 2;
            } else if (this.semester.equals("Fall")) {
                this.semesterVal = 3;
            } else if (this.semester.equals("Winter")) {
                this.semesterVal = 4;
            }
        }

        @Override
        public int compareTo(OfficeHour other) {
            setSemesterVal();
            if (this.year < other.year) {
                return 1;
            } else if (this.year > other.year) {
                return -1;
            } else {
                if (this.semesterVal < other.semesterVal) {
                    return 1;
                } else if (this.semesterVal > other.semesterVal) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }

    }

    @FXML
    public void initialize() {
//        try {
//            File file = new File("sample.txt");
//            Scanner sc = new Scanner(file);
//            String line = "";
//            String[] elements;
//            ObservableList<OfficeHour> data = FXCollections.observableArrayList();
//
//            while (sc.hasNextLine()) {
//                line = sc.nextLine();
//                elements = line.split(",");
//                data.add(new OfficeHour(elements[0], Integer.parseInt(elements[1]), elements[2]));
//            }
//
//            // Set up columns
//            semesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));
//            yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
//            weekdayColumn.setCellValueFactory(new PropertyValueFactory<>("weekdays"));
//
//            Collections.sort(data);
//            tableView.setItems(data);
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }


        // Set up columns
        semesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        weekdayColumn.setCellValueFactory(new PropertyValueFactory<>("weekdays"));

        // Populate data
        ObservableList<OfficeHour> data = FXCollections.observableArrayList(
                new OfficeHour("Fall", 2024, "Tuesday Thursday"),
                new OfficeHour("Spring", 2025, "Monday Wednesday"),
                new OfficeHour("Winter", 2025, "Friday"),
                new OfficeHour("Summer", 2023, "Monday Wednesday"),
                new OfficeHour("Fall", 2025, "Tuesday Thursday"),
                new OfficeHour("Summer", 2025, "Wednesday Friday")
        );

        Collections.sort(data);

        tableView.setItems(data);
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