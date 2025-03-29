package s25.cs151.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DefineCourseController {

    @FXML
    private TextField courseCode;

    @FXML
    private TextField courseName;

    @FXML
    Pane coursePane;

    @FXML
    private Button previousPage;

    @FXML
    private TextField sectionNumber;



    @FXML
    void onFinalSubmit(ActionEvent event) {
        saveCourse();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home-page.fxml"));
            Pane pane = fxmlLoader.load();
            coursePane.getChildren().clear();
            coursePane.getChildren().add(pane);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onPreviousButton(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home-page.fxml"));
            Pane pane = fxmlLoader.load();
            coursePane.getChildren().clear();
            coursePane.getChildren().add(pane);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    boolean isValidEntry(String entry) {
        if (entry.isEmpty()) {
            return false;
        }
        return true;
    }


    @FXML
    protected void saveCourse() {
        String csvFilePath = "courses.csv";

        boolean isDuplicate = false;

        try {
            File file = new File("courses.csv");
            Scanner sc = new Scanner(file);
            String line = "";

            String courseCodeValue = courseCode.getText().strip();
            String courseNameValue = courseName.getText().strip();
            String sectionNumberValue = sectionNumber.getText().strip();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incomplete Entry");
            alert.setHeaderText(null);
            if (!isValidEntry(courseCodeValue)) {
                alert.setContentText("Course code must be provided.");
                alert.showAndWait();
                return;
            } else if (!isValidEntry(courseNameValue)) {
                alert.setContentText("Course name must be provided.");
                alert.showAndWait();
                return;
            } else if (!isValidEntry(sectionNumberValue)) {
                alert.setContentText("Section number must be provided.");
                alert.showAndWait();
                return;
            }

//            if(!validateYear(year)) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Invalid Year");
//                alert.setHeaderText(null);
//                alert.setContentText("Year must be a 4 digit number.");
//                alert.showAndWait();
//                return;
//            }


            sc.nextLine();
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String[] elements = line.split(",");

                if (elements.length >= 3) {
                    if (elements[0].trim().equals(courseCodeValue) && elements[1].trim().equals(courseNameValue) && elements[2].trim().equals(sectionNumberValue)) {
                        isDuplicate = true;
                        break;
                    }
                }
            }
//            sc.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (isDuplicate) {
            System.out.println("Duplicate Course Code, Course Name, and Section - cannot add");
            return;
        }

        try(FileWriter writer = new FileWriter(csvFilePath, true)) {
//            if (new java.io.File(csvFilePath).length() > 0) {
//                writer.append("\n");
//            }
            writer.append(courseCode.getText()).append(',').append(courseName.getText()).append(',').append(sectionNumber.getText()).append('\n');
            writer.flush();
            System.out.println("Done for the time slot");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

}
