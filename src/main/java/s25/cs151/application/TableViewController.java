package s25.cs151.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TableViewController implements Initializable {

    @FXML
    private TableView<OfficeHour> table;
    @FXML
    private TableColumn<OfficeHour, String> semester;
    @FXML
    private TableColumn<OfficeHour, String> year;
    @FXML
    private TableColumn<OfficeHour, String> days;

    private ObservableList<OfficeHour> initialData() {
        OfficeHour p1 = new OfficeHour("Spring", "2003", "Wednesday Tuesday Friday");
        OfficeHour p2 = new OfficeHour("Fall", "2004", "Monday Wednesday");
        return FXCollections.observableArrayList(p1, p2);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        semester.setCellValueFactory(new PropertyValueFactory<>("semester"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        days.setCellValueFactory(new PropertyValueFactory<>("days"));

        table.setItems(initialData());
    }
}