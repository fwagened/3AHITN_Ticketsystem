package applications.controller;

import applications.model.Priority;
import applications.model.Status;
import applications.model.Ticket;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TicketsController {
    public TextField nameField;
    public ComboBox<Priority> priorityBox;
    public ComboBox<Status> statusBox;
    public TextArea beschreibungsFeld;
    public TextField nrField;
    private Ticket ticket;

    public void setTicket(Ticket t) {
        this.ticket = t;

        /**
         * Darstellen der Daten des Tickets
         */
        nrField.setText(Integer.toString(t.id));
        nameField.setText(t.name);
        beschreibungsFeld.setText(t.berschreibung);
        statusBox.setItems(Status.loadlist());
        priorityBox.setItems(Priority.loadlist());

        for (Status s : statusBox.getItems()) {
            if (s.id == t.status.id) {
                statusBox.getSelectionModel().select(s);
                break;
            }
        }

        for (Priority p : priorityBox.getItems()) {
            if (p.id == t.prioritaet.id) {
                priorityBox.getSelectionModel().select(p);
                break;
            }
        }
    }

    public Ticket getTicket() {
        /**
         * aktualisieren der Ticket - Daten
         */
        ticket.id = Integer.parseInt(nrField.getText());
        ticket.name = nameField.getText();
        ticket.berschreibung = beschreibungsFeld.getText();
        ticket.status = statusBox.getValue();
        ticket.prioritaet = priorityBox.getValue();

        return  ticket;
    }
}
