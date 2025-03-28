package s25.cs151.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.FileWriter;
import java.io.IOException;

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

    @FXML
    protected void saveCourse() {
        String csvFilePath = "courses.csv";
        try(FileWriter writer = new FileWriter(csvFilePath, true)) {
            if (new java.io.File(csvFilePath).length() > 0) {
                writer.append("\n");
            }
            writer.append(courseCode.getText()).append(',').append(courseName.getText()).append(',').append(sectionNumber.getText()).append('\n');
            writer.flush();
            System.out.println("Done for the time slot");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

}
