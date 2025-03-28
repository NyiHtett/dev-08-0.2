package s25.cs151.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Pane;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

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
        return startTimeHour.getValue() + ":" + startTimeMinute.getValue();
    }

    public String getEndTime() {
        return endTimeHour.getValue() + ":" + endTimeMinute.getValue();
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
        String csvFilePath = "time_slots.csv";
        try(FileWriter writer = new FileWriter(csvFilePath, true)) {
            if (new java.io.File(csvFilePath).length() > 0) {
                writer.append("\n");
            }
            writer.append(getStartTime()).append(',').append(getEndTime()).append('\n');
            writer.flush();
            System.out.println("Done for the time slot");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }



}
