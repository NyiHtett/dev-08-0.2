package s25.cs151.application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import s25.cs151.application.Main;

import java.io.FileWriter;
import java.io.IOException;

public class DefineTimeSlotsController {

    @FXML
    private Button coursePage;

    @FXML
    private Button previousPage;

    @FXML
    private Spinner<Integer> endTimeHour;

    @FXML
    private Spinner<Integer> endTimeMinute;

    @FXML
    private Spinner<Integer> startTimeHour;

    @FXML
    private Spinner<Integer> startTimeMinute;

    public String getStartTime() {
        // Add leading zero
        return String.format("%02d:%02d", startTimeHour.getValue(), startTimeMinute.getValue());
    }

    public String getEndTime() {
        return String.format("%02d:%02d", endTimeHour.getValue(), endTimeMinute.getValue());
    }

    @FXML
    public void initialize() {
        startTimeHour.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 9));
        startTimeMinute.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));

        endTimeHour.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 16));
        endTimeMinute.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
    }

    @FXML
    Pane timeSlotPane;

    @FXML
    protected void onCoursePageClick() {
        this.saveTimeSlot();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home-page.fxml"));
            Pane pane = fxmlLoader.load();
            timeSlotPane.getChildren().clear();
            timeSlotPane.getChildren().add(pane);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onPreviousPageClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home-page.fxml"));
            Pane pane = fxmlLoader.load();
            timeSlotPane.getChildren().clear();
            timeSlotPane.getChildren().add(pane);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void saveTimeSlot() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if( startTimeHour.getValue() > endTimeHour.getValue()){
            alert.setContentText("Start time must be before end time.");
            alert.showAndWait();
            return;
        } else if (startTimeHour.getValue().intValue() == endTimeHour.getValue().intValue() && startTimeMinute.getValue() > endTimeMinute.getValue()){
            alert.setContentText("Start time must be before end time.");
            alert.showAndWait();
            return;
        }
        String csvFilePath = "src/csv_files/time_slots.csv";
        try(FileWriter writer = new FileWriter(csvFilePath, true)) {
//            if (new java.io.File(csvFilePath).length() > 0) {
//                writer.append("\n");
//            }
            writer.append(getStartTime()).append(',').append(getEndTime()).append('\n');
            writer.flush();
            System.out.println("Done for the time slot");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }



}
