package applications.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Priority {
    public int id;
    public String name;

    public String toString() {
        return id + " - " + name;
    }

    public static ObservableList<Priority> loadFile() {
        ObservableList<Priority> result = FXCollections.observableArrayList();
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

                    result.add(p); // füge Artikel zur Liste hinzu
                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
        }

        return result;
    }
}
