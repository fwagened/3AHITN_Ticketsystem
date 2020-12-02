package applications.controller;

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
    public ComboBox abteilungBox;
    public TextField landField;
    public Button abbrechenButton;
    public Button neuButton;
    public Button loeschenButton1;
    public ListView<User> listViewUser;
    public TextField idField;

    ObservableList<User> list = FXCollections.observableArrayList();

    private User selectedUser = null;

    public void initialize() {
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("users.csv"));
            try {
                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    s = s.replace("\"", ""); // ersetze alle " in der Zeile
                    User u = new User();

                    String[] words = s.split(";");
                    u.id = Integer.parseInt(words[0]);
                    u.title = words[1];
                    u.name = words[2];
                    u.street = words[3];
                    u.plz = Integer.parseInt(words[4]);
                    u.city = words[5];
                    u.land = words[6];
                    u.abteilung = Integer.parseInt(words[7]);

                    list.add(u); // füge Artikel zur Liste hinzu
                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
        }

        listViewUser.setItems(list);
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
