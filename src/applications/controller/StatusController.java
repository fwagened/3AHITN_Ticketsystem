package applications.controller;

import applications.model.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StatusController {
    public ListView<Status> listViewStati;
    public Button loeschenButton;
    public Button speichernButton;
    public TextField statusTextfield;


    ObservableList<Status> list = FXCollections.observableArrayList();


    public void initialize() {
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("stati.csv"));
            try {
                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    s = s.replace("\"", ""); // ersetze alle " in der Zeile
                    Status status = new Status();

                    String[] words = s.split(";");
                    status.id = Integer.parseInt(words[0]);
                    status.status = words[1];

                    list.add(status);
                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
        }

        listViewStati.setItems(list);
    }

    public void loeschenButtonClicked(ActionEvent actionEvent) {
    }

    public void speichernButtonClicked(ActionEvent actionEvent) {
    }
}
