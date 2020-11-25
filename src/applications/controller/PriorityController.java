package applications.controller;

import applications.model.Priority;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PriorityController {
    public TextField prioritiTextField;
    public ListView<Priority> listViewPriorities;

    ObservableList<Priority> list = FXCollections.observableArrayList();

    public void initialize() {
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("priorities.csv"));
            try {
                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    s = s.replace("\"", ""); // ersetze alle " in der Zeile
                    Priority p = new Priority();

                    String[] words = s.split(";");
                    p.id = Integer.parseInt(words[0]);
                    p.name = words[1];

                    list.add(p); // füge Artikel zur Liste hinzu
                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
        }

        listViewPriorities.setItems(list);
    }

    public void saveButtonClicked(ActionEvent actionEvent) {
        
    }

    public void abbrechenButtonCLicked(ActionEvent actionEvent) {

    }
}
