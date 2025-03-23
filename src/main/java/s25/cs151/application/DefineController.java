package s25.cs151.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class DefineController {

    @FXML
    Pane definePane;

    @FXML
    private TextField yearField;

    @FXML
    private ComboBox<String> semesterBox;

    @FXML
    private ArrayList<String> selectedDays;

    @FXML
    private CheckBox wed;
    @FXML
    private CheckBox tue;
    @FXML
    private CheckBox mon;
    @FXML
    private CheckBox fri;
    @FXML
    private CheckBox thu;

    /**
     * Saving every single user's assignment to the csv file line by line
     * @param semester
     * @param year
     * @param days
     */
    @FXML
    private void saveDataToCSV(String semester, String year, ArrayList<String> days) {
        String csvFilePath = "semester_office_hours.csv";
        try(FileWriter writer = new FileWriter(csvFilePath, true)) {
            writer.append("\n");
            writer.append(semester).append(',').append(year).append(',');
            for( String day : days ) {
                writer.append(day).append(' ');
            }
            writer.flush();
            System.out.println("Done");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }


    // Adds options for semester's dropdown box
    @FXML
    public void initialize() {
        // Hardcode the options for the ComboBox
        semesterBox.getItems().addAll("Spring", "Summer", "Fall", "Winter");
    }

    // Directs user to home page
    @FXML
    protected void onCancelClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home-page.fxml"));
            Pane pane = fxmlLoader.load();

            definePane.getChildren().clear();
            definePane.getChildren().add(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save the data to flatfile
    @FXML
    protected void onSubmitClick() {
        String semester = semesterBox.getSelectionModel().getSelectedItem();
        if(semester == null) {
            semester = "Spring";
        }
        System.out.println(semester);
        /**
         * having error retrieving year from yearField
         * thus hardcoded the year as 2003
         */
        //System.out.println(yearField.getText());
        ArrayList<String> days = new ArrayList<>();
        if(wed.isSelected()) {
            days.add("Wednesday");
        }
        if(tue.isSelected()) {
            days.add("Tuesday");
        }
        if(mon.isSelected()) {
            days.add("Monday");
        }
        if(fri.isSelected()) {
            days.add("Friday");
        }
        if(thu.isSelected()) {
            days.add("Thursday");
        }
        saveDataToCSV(semester, "2003", days);
    }

}