package applications.controller;

import applications.MyFXMLLoader;
import applications.model.Priority;
import applications.model.Status;
import applications.model.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Controller {

    public ListView<Ticket> ticketListView;
    public AnchorPane contentPane;
    // neue Felder
    /**
     * Filter müssen UND - Verknüpft werden!!!
     */
    public TextField filterNameTextfield;
    public ComboBox<Status> filterStatusComboBox;
    public ComboBox<Priority> filterPrioritätComboBox;

    public TicketsController active = null;

    ObservableList<Ticket> list = FXCollections.observableArrayList();
    ObservableList<Ticket> searchlist = FXCollections.observableArrayList();

    public Ticket selectedTicket = null;

    public void editStatiClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/stati.fxml", "Stati bearbeiten");
    }

    public void editPrioritiesClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/priorities.fxml", "Prioritäten bearbeiten");
    }


    public void editDepartmentClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/department.fxml", "Abteilungen bearbeiten");
    }

    public void editUsersClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/user.fxml", "Users bearbeiten");
    }

    public void initialize() {
        ticketListView.setItems(Ticket.loadFile("tickets.csv"));
        filterStatusComboBox.setItems(Status.loadlist());
        filterPrioritätComboBox.setItems(Priority.loadlist());
        list = Ticket.loadFile("ticket.csv");
    }

    public void ticketListViewClicked(MouseEvent mouseEvent) {

        MyFXMLLoader loader = new MyFXMLLoader();
        Parent root = loader.loadFXML("view/tickets.fxml");

        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);

        contentPane.getChildren().add(root);


       active = (TicketsController) loader.getController();
       active.setTicket(ticketListView.getSelectionModel().getSelectedItem());

       Ticket selected = ticketListView.getSelectionModel().getSelectedItem();
       this.selectedTicket = selected;
    }

    public void searchTicket(KeyEvent keyEvent) {
        String searchedItem = filterNameTextfield.getText();
        searchlist.clear();

        for (Ticket t : list) {
            if (t.name.contains(searchedItem) || Integer.toString(t.id).contains(searchedItem)) {
                searchlist.add(t);
            }

        }
        ticketListView.setItems(searchlist);
    }

    public void searchStatus(MouseEvent mouseEvent) {
        Status searchedItem = filterStatusComboBox.getValue();
        searchlist.clear();

        for (Ticket t : list) {
            if (t.status == searchedItem.id) {
                searchlist.add(t);
            }
        }
        ticketListView.setItems(searchlist);
    }

    public void searchPrioritaet(MouseEvent mouseEvent) {
        Priority searchedItem = filterPrioritätComboBox.getValue();
        searchlist.clear();

        for (Ticket t : list) {
            if (t.prioritaet == searchedItem.id) {
                searchlist.add(t);
            }
        }
        ticketListView.setItems(searchlist);
    }

    public void newClicked(ActionEvent actionEvent) {
        contentPane.getChildren().clear();
        MyFXMLLoader loader = new MyFXMLLoader();
        Parent root = loader.loadFXML("view/tickets.fxml");
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
        contentPane.getChildren().add(root);

        active = (TicketsController) loader.getController();
        active.nrField.clear();
        active.nameField.clear();
        active.beschreibungsFeld.clear();
        active.statusBox.setItems(Status.loadlist());
        active.priorityBox.setItems(Priority.loadlist());

        this.selectedTicket = null;

    }

    public void deleteClicked(ActionEvent actionEvent) {
        //laden des Tickets
        //entfernen aus liste
        //Datei aktualisieren

        active.nrField.clear();
        active.nameField.clear();
        active.beschreibungsFeld.clear();
        active.statusBox.setItems(null);
        active.priorityBox.setItems(null);

        list.remove(selectedTicket);

        ticketListView.refresh();

        writeTicketToFile();
    }

    public void saveClicked(ActionEvent actionEvent) {
        if (this.selectedTicket != null) {
            selectedTicket.id = Integer.parseInt(active.nrField.getText());
            selectedTicket.name = active.nameField.getText();
            selectedTicket.berschreibung = active.beschreibungsFeld.getText();
            selectedTicket.prioritaet = active.priorityBox.getSelectionModel().getSelectedItem().id;
            selectedTicket.status = active.statusBox.getSelectionModel().getSelectedItem().id;

            ticketListView.refresh();

            ticketListView.setItems(list);
        } else {
            Ticket newTicket = new Ticket();

            newTicket.id = Integer.parseInt(active.nrField.getText());
            newTicket.name = active.nameField.getText();
            newTicket.berschreibung = active.beschreibungsFeld.getText();
            newTicket.prioritaet = active.priorityBox.getSelectionModel().getSelectedItem().id;
            newTicket.status = active.statusBox.getSelectionModel().getSelectedItem().id;

            ticketListView.getItems().add(newTicket);
        }

        writeTicketToFile();
    }

    private void writeTicketToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("tickets.csv"));

            for (Ticket t : ticketListView.getItems()) {
                bw.write(t.newCSVLine());
            }
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
