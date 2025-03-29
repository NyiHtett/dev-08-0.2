package s25.cs151.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class TimeSlotsTableViewController {
    @FXML
    private AnchorPane timeSlotsPane;

    @FXML
    private TableView<TimeSlot> table;

    @FXML
    private TableColumn<TimeSlot, String> fromHour;

    @FXML
    private TableColumn<TimeSlot, String> toHour;

    public static class TimeSlot implements Comparable<TimeSlot> {
        private String fromHour;
        private String toHour;

        public TimeSlot(String fromHour, String toHour) {
            this.fromHour = fromHour;
            this.toHour = toHour;
        }

        public String getFromHour() {
            return this.fromHour;
        }

        public String getToHour() {
            return this.toHour;
        }

        @Override
        public int compareTo(TimeSlot other) {
            String[] split = this.fromHour.split(":");
            int hr = Integer.parseInt(split[0]);
            int min = Integer.parseInt(split[1]);

            String[] otherSplit = other.fromHour.split(":");
            int otherHr = Integer.parseInt(otherSplit[0]);
            int otherMin = Integer.parseInt(otherSplit[1]);

            if (hr < otherHr) {
                return -1;
            } else if (hr > otherHr) {
                return 1;
            } else {
                if (min < otherMin) {
                    return -1;
                } else if (min > otherMin) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }

    }

    @FXML
    public void initialize() {
        try {
            File file = new File("time_slots.csv");
            Scanner sc = new Scanner(file);

            String line = "";
            String[] elements;
            ObservableList<TimeSlot> data = FXCollections.observableArrayList();

            sc.nextLine();
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                elements = line.split(",");

                TimeSlot timeSlot = new TimeSlot(elements[0],elements[1]);
                data.add(timeSlot);
            }

            // Set up columns
            fromHour.setCellValueFactory(new PropertyValueFactory<>("fromHour"));
            toHour.setCellValueFactory(new PropertyValueFactory<>("toHour"));

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

            timeSlotsPane.getChildren().clear();
            timeSlotsPane.getChildren().add(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
