package applications.controller;

import applications.model.Department;
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


    public void initialize() {
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("departments.csv"));
            try {
                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    s = s.replace("\"", ""); // ersetze alle " in der Zeile
                    Department department = new Department();

                    String[] words = s.split(";");
                    department.departmentId = Integer.parseInt(words[0]);
                    department.departmentName = words[1];

                    list.add(department);
                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
            io.printStackTrace();
        }

        listViewAbteilung.setItems(list);
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
