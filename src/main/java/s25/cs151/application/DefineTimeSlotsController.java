package s25.cs151.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class DefineTimeSlotsController {

    @FXML
    private Button coursePage;

    @FXML
    private DatePicker endTime;

    @FXML
    private Button previousPage;

    @FXML
    private DatePicker startTime;

    public LocalDate getStartTime() {
        return startTime.getValue();
    }

    public LocalDate getEndTime() {
        return endTime.getValue();
    }

    @FXML
    Pane timeSlotPane;

    @FXML
    protected void onCoursePageClick() {
        this.saveTimeSlot();
        timeSlotPane.getChildren().clear();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("define-office-hours.fxml"));
            Pane pane = fxmlLoader.load();
            timeSlotPane.getChildren().add(pane);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onPreviousPageClick() {

    }

    protected void saveTimeSlot() {
        String csvFilePath = "semester_office_hours.csv";
        try(FileWriter writer = new FileWriter(csvFilePath, true)) {
            writer.append(",");
            writer.append(this.getStartTime().toString()).append(',').append(this.getEndTime().toString()).append(',');
            writer.flush();
            System.out.println("Done for the time slot");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }


}
