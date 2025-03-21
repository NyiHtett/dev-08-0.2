package s25.cs151.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class DefineController {

    @FXML
    Pane definePane;

    @FXML
    private Text yearText;

    @FXML
    private ComboBox<String> semesterBox;

    @FXML
    private ArrayList<String> chosenDays;

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



    // Adds options for semester's dropdown box
    @FXML
    public void initialize() {
        // Hardcode the options for the ComboBox
        semesterBox.getItems().addAll("Summer", "Fall", "Winter");
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
        System.out.println(semesterBox.getSelectionModel().getSelectedItem());
        System.out.println(yearText);
        if(wed.isSelected()) {
            chosenDays.add("Wednesday");
        }
        if(tue.isSelected()) {
            chosenDays.add("Tuesday");
        }
        if(mon.isSelected()) {
            chosenDays.add("Monday");
        }
        if(fri.isSelected()) {
            chosenDays.add("Friday");
        }
        if(thu.isSelected()) {
            chosenDays.add("Thursday");
        }
        System.out.println(chosenDays);
    }

}