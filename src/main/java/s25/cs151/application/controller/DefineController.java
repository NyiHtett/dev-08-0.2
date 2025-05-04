package s25.cs151.application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import s25.cs151.application.Main;

public class DefineController  {

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

        String csvFilePath = "src/csv_files/semester_office_hours.csv";

        boolean isDuplicate = false;

        try {
            File file = new File("src/csv_files/semester_office_hours.csv");
            Scanner sc = new Scanner(file);
            String line = "";

            sc.nextLine();
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String[] elements = line.split(",");
                if (elements.length >= 2 &&
                        elements[0].trim().equals(semester) &&
                        elements[1].trim().equals(year)) {
                    isDuplicate = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (isDuplicate) {
            System.out.println("Duplicate semester and year found - cannot add");
            return;
        }

        try(FileWriter writer = new FileWriter(csvFilePath, true)) {
            // Only add newline if file is not empty
//            if (new java.io.File(csvFilePath).length() > 0) {
//                writer.append("\n");
//            }
            writer.append(semester).append(',').append(year).append(',');
            // Join days with spaces
            writer.append(String.join(" ", days));
            writer.append('\n');
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
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/home-page.fxml"));
            Pane pane = fxmlLoader.load();

            definePane.getChildren().clear();
            definePane.getChildren().add(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Validates if year entered is a four digit integer.
    @FXML
    private boolean validateYear(String input){
        if(input.length() == 4){
            for(int i = 0; i < input.length(); i++){
                if(!Character.isDigit(input.charAt(i))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    // Save the data to flatfile
    @FXML
    protected void onSubmitClick() {
        String semester = semesterBox.getSelectionModel().getSelectedItem();

        if(semester == null) {
            semester = "Spring";
        }

        String year = yearField.getText();

        if(!validateYear(year)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Year");
            alert.setHeaderText(null);
            alert.setContentText("Year must be a 4 digit number.");
            alert.showAndWait();
            return;
        }

        ArrayList<String> days = new ArrayList<>();
        if(mon.isSelected()) {
            days.add("Monday");
        }
        if(tue.isSelected()) {
            days.add("Tuesday");
        }
        if(wed.isSelected()) {
            days.add("Wednesday");
        }
        if(thu.isSelected()) {
            days.add("Thursday");
        }
        if(fri.isSelected()) {
            days.add("Friday");
        }

        if(days.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Days");
            alert.setHeaderText(null);
            alert.setContentText("Select at least one day.");
            alert.showAndWait();
            return;
        }

        saveDataToCSV(semester, year, days);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/home-page.fxml"));
            Pane pane = fxmlLoader.load();

            definePane.getChildren().clear();
            definePane.getChildren().add(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
