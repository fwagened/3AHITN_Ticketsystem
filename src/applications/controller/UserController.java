package applications.controller;

import applications.model.Department;
import applications.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserController {
    public TextField titleField;
    public TextField nameField;
    public TextField streetField;
    public TextField plzField;
    public TextField cityField;
    public ComboBox<Department> abteilungBox;
    public TextField landField;
    public Button abbrechenButton;
    public Button neuButton;
    public Button loeschenButton1;
    public ListView<User> listViewUser;
    public TextField idField;

    private User selectedUser = null;

    public void initialize () {
        listViewUser.setItems(User.loadlist());
        abteilungBox.setItems(Department.loadlist());
    }

    public void listViewClicked(MouseEvent mouseEvent) {
        User selected = listViewUser.getSelectionModel().getSelectedItem();

        if (selected != null) {
            this.selectedUser = selected;

            idField.setText(Integer.toString(selected.id));
            titleField.setText(selected.title);
            nameField.setText(selected.name);
            streetField.setText(selected.street);
            plzField.setText(Integer.toString(selected.plz));
            cityField.setText(selected.city);
            landField.setText(selected.land);
            abteilungBox.setValue(selected.abteilung);
        }
    }

    public void abbrechenButtonCLicked(ActionEvent actionEvent) {
        Stage stage = (Stage) abbrechenButton.getScene().getWindow();

        stage.close();
    }

    public void saveButtonClicked(ActionEvent actionEvent) {
        if (selectedUser != null) {
            selectedUser.name = nameField.getText();
            selectedUser.id = Integer.parseInt(idField.getText());
            selectedUser.title = titleField.getText();
            selectedUser.plz = Integer.parseInt(plzField.getText());
            selectedUser.city = cityField.getText();
            selectedUser.land = landField.getText();
            selectedUser.street = streetField.getText();
            selectedUser.abteilung = abteilungBox.getValue();

            listViewUser.refresh();

            selectedUser.update(); // aktualisiere in Datenbank
        }
    }

    public void neuButtonCLicked(ActionEvent actionEvent) {

    }

    public void loeschenButtonCLicked(ActionEvent actionEvent) {
        titleField.clear();
        nameField.clear();
        streetField.clear();
        plzField.clear();
        cityField.clear();
        landField.clear();
        idField.clear();

        listViewUser.getItems().remove(selectedUser);

        selectedUser.delete();

    }
}
