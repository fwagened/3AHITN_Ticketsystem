package applications.controller;

import applications.MyFXMLLoader;
import applications.model.Priority;
import applications.model.Status;
import applications.model.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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

    private TicketsController active = null;

    ObservableList<Ticket> list = FXCollections.observableArrayList();
    ObservableList<Ticket> searchlist = FXCollections.observableArrayList();

    ObservableList<Priority> priorityList = FXCollections.observableArrayList();
    ObservableList<Priority> prioritySearchlist = FXCollections.observableArrayList();

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
        filterStatusComboBox.setItems(Status.loadFile("stati.csv"));
        filterPrioritätComboBox.setItems(Priority.loadFile("priorities.csv"));
        list = Ticket.loadFile("ticket.csv");
    }

    public void ticketListViewClicked(MouseEvent mouseEvent) {

        MyFXMLLoader loader = new MyFXMLLoader();
        Parent root = loader.loadFXML("view/tickets.fxml");
        contentPane.getChildren().add(root);

       active = (TicketsController) loader.getController();
       active.setTicket(ticketListView.getSelectionModel().getSelectedItem());
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

    }

    public void searchPrioritaet(MouseEvent mouseEvent) {
        String searchedItem = filterPrioritätComboBox.getItems();
        searchlist.clear();

        for (Ticket t : list) {
            if (t.name.contains(searchedItem) || Integer.toString(t.id).contains(searchedItem)) {
                searchlist.add(t);
            }

        }
        ticketListView.setItems(searchlist);
    }

    public void newClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        Parent root = loader.loadFXML("view/ticket.fxml");
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
        contentPane.getChildren().add(root);

        active = (TicketsController) loader.getController();
        active.setTicket(null);

    }

    public void deleteClicked(ActionEvent actionEvent) {
        //laden des Tickets
        //entfernen aus liste
        //Datei aktualisieren



    }

    public void saveClicked(ActionEvent actionEvent) {
        //Wenn Ticket neu -> laden des Tickets hinzufügen zur Liste
        //Datei aktualisieren
    }
}
