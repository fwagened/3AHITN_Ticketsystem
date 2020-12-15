package applications.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ticket {
    public int id;
    public String name;
    public String berschreibung;
    public int status;
    public int priorität;

    public String toString() {
        return id + " - " + name;
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
                    t.priorität = Integer.parseInt(words[4]);

                    result.add(t);
                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
        }

        return result;
    }
}
