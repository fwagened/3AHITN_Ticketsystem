package applications.controller;

import applications.model.Priority;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PriorityController {
    public TextField prioritiTextField;
    public ListView<Priority> listViewPriorities;
    public Button abbrechenButton;
    public Button neuButton;
    public Button loeschenButton1;

    private Priority selectedPriority = null;

    public void initialize() {
        listViewPriorities.setItems(Priority.loadFile());
    }


    public void listViewClicked(MouseEvent mouseEvent) {
        Priority selected = listViewPriorities.getSelectionModel().getSelectedItem();

        if (selected != null) {
            this.selectedPriority = selected;

            prioritiTextField.setText(selected.name);
        }
    }

    public void abbrechenButtonCLicked(ActionEvent actionEvent) {
        Stage stage = (Stage) abbrechenButton.getScene().getWindow();

        stage.close();
    }

    public void saveButtonClicked(ActionEvent actionEvent) {
        
    }

    public void neuButtonCLicked(ActionEvent actionEvent) {
    }

    public void loeschenButtonCLicked(ActionEvent actionEvent) {
    }
}
