package applications.controller;

import applications.model.Status;
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

public class StatusController {
    public ListView<Status> listViewStati;
    public Button loeschenButton;
    public Button speichernButton;
    public TextField statusTextfield;
    public Button abbrechenButton;


    public void initialize () {
        listViewStati.setItems(Status.loadFile("stati.csv"));
    }


    private Status selectedStatus = null;


    public void loeschenButtonClicked(ActionEvent actionEvent) {
    }

    public void speichernButtonClicked(ActionEvent actionEvent) {
    }

    public void abbrechenButtonClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) abbrechenButton.getScene().getWindow();
        stage.close();



    }

    public void listViewClicked(MouseEvent mouseEvent) {
        Status selected = listViewStati.getSelectionModel().getSelectedItem();

        if (selected != null) {
            this.selectedStatus = selected;

            statusTextfield.setText(selected.status);

        }
    }

    public void neuButtonClicked(ActionEvent actionEvent) {
    }
}
