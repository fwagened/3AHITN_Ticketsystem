package applications.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ticket {
    public int id;
    public String name;
    public String berschreibung;
    public Status status;
    public Priority prioritaet;

    public String toString() {
        return id + " - " + name;
    }

    public Ticket(int id, String name, String beschreibung, int statusId, int prioritaet) {
        this.id = id;
        this.name = name;
        this.berschreibung = beschreibung;

        this.status = Status.getById(statusId);
        this.prioritaet = Priority.getById(prioritaet);
    }

    public static  ObservableList<Ticket> loadlist() {
        ObservableList<Ticket> list = FXCollections.observableArrayList();

        try{
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM tickets");

            while (result.next()) {
                Ticket t = new Ticket(result.getInt("ticket_id"),
                        result.getString("name"),
                        result.getString("desc"),
                        result.getInt("status_id"),
                        result.getInt("priority_id"));

                list.add(t);
            }
        } catch (SQLException throwables){

        }

        return list;
    }

    public static ObservableList<Ticket> loadFile(String filename) {
        ObservableList<Ticket> result = FXCollections.observableArrayList();
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("tickets.csv"));
            try {
                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    s = s.replace("\"", ""); // ersetze alle " in der Zeile
                    String[] words = s.split(";");
                    Ticket t = new Ticket(Integer.parseInt(words[0]),
                            words[1],
                            words[2],
                            Integer.parseInt(words[3]),
                            Integer.parseInt(words[4]));

                    result.add(t);
                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
        }

        return result;
    }

    public String newCSVLine() {
        return id + ";" + name + ";" + berschreibung + ";" + status + ";" + prioritaet +  "\n";
    }
}
