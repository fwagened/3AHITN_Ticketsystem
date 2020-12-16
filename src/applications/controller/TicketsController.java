package applications.controller;

import applications.model.Priority;
import applications.model.Status;
import applications.model.Ticket;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TicketsController {
    public TextField nameField;
    public ComboBox priorityBox;
    public ComboBox statusBox;
    public TextArea beschreibungsFeld;
    public TextField nrField;

    public void setTicket(Ticket t) {
        nrField.setText(Integer.toString(t.id));
        nameField.setText(t.name);
        beschreibungsFeld.setText(t.berschreibung);
        priorityBox.getSelectionModel().select(Integer.toString(t.priorität));
        priorityBox.setItems(Priority.loadFile("prioties.csv"));
        statusBox.getSelectionModel().select(Integer.toString(t.status));
        statusBox.setItems(Status.loadFile("stati.csv"));
    }


}
