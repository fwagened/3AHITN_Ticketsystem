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
    public int status;
    public int prioritaet;

    public String toString() {
        return id + " - " + name;
    }

    public static  ObservableList<Ticket> loadlist() {
        ObservableList<Ticket> list = FXCollections.observableArrayList();

        try{
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM tickets");

            while (result.next()) {
                Ticket t = new Ticket();
                t.id = result.getInt("ticket_id");
                t.name = result.getString("name");
                t.berschreibung = result.getString("desc");
                t.prioritaet = Integer.parseInt(result.getString("priority_id"));
                t.status = Integer.parseInt(result.getString("status_id"));

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
                    Ticket t = new Ticket();

                    String[] words = s.split(";");
                    t.id = Integer.parseInt(words[0]);
                    t.name = words[1];
                    t.berschreibung = words[2];
                    t.status = Integer.parseInt(words[3]);
                    t.prioritaet = Integer.parseInt(words[4]);

                    result.add(t);
                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
        }

        return result;
    }

    public String newCSVLine(){
        return id + ";" + name + ";" + berschreibung + ";" + status + ";" + prioritaet +  "\n";
    }
}
