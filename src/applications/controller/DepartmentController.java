package applications.controller;

import applications.model.Department;
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

public class DepartmentController {


    public Button speichernButton;
    public Button loeschenButton;
    public Button abbrechenButton;
    public Button neuButton;
    public TextField abteilungTextfield;
    public ListView<Department> listViewAbteilung;

    ObservableList<Department> list = FXCollections.observableArrayList();

    private Department selectedDepartment = null;


    public void initialize () {
        listViewAbteilung.setItems(Department.loadlist());
    }




    public void speichernButtonClicked(ActionEvent actionEvent) {
    }

    public void loeschenButtonlicked(ActionEvent actionEvent) {
    }

    public void neuButtonClicked(ActionEvent actionEvent) {
    }

    public void listViewDepartmentClicked(MouseEvent mouseEvent) {
        Department selected = listViewAbteilung.getSelectionModel().getSelectedItem();

        if (selected != null) {
            this.selectedDepartment = selected;

            abteilungTextfield.setText(selected.departmentName);

        }
    }

    public void abbrechenButtonClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) abbrechenButton.getScene().getWindow();
        stage.close();
    }
}
