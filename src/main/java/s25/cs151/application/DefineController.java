package s25.cs151.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;


public class DefineController {

    @FXML
    Pane definePane;

    @FXML
    private ComboBox<String> semesterBox;


    @FXML
    public void initialize() {
        // Hardcode the options for the ComboBox
        semesterBox.getItems().addAll("Summer", "Fall", "Winter");
    }
}