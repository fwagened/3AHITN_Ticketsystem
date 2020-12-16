package applications.controller;

import applications.model.Priority;
import applications.model.Status;
import applications.model.Ticket;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TicketsController {
    public TextField nameField;
    public ComboBox priorityBox;
    public ComboBox statusBox;
    public TextArea beschreibungsFeld;
    public TextField nrField;
    public Button abbrechenButton;
    public Button saveButton;
    public Button neuButton;
    public Button loeschenButton1;
    private Ticket ticket;

    public void setTicket(Ticket t) {
        this.ticket = t;

        /**
         * Darstellen der Daten des Tickets
         */
        nameField.setText(t.name);
        beschreibungsFeld.setText(t.berschreibung);
        statusBox.setItems(Status.loadFile("stati.csv"));
        priorityBox.setItems(Priority.loadFile("priorities.csv"));

        for (Status s : statusBox.getItems()) {
            if (s.id == t.status) {
                statusBox.getSelectionModel().select(s);
                break;
            }
        }

        for (Priority p : priorityBox.getItems()) {
            if (p.id == t.priorität) {
                priorityBox.getSelectionModel().select(p);
                break;
            }
        }
    }

    public Ticket getTicket() {
        /**
         * aktualisieren der Ticket - Daten
         */
        ticket.name = nameField.getText();
        // ....

        return  ticket;
    }
}
