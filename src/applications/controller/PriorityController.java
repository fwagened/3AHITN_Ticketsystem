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

    ObservableList<Priority> list = FXCollections.observableArrayList();

    private Priority selectedPriority = null;

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
