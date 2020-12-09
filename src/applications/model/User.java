package applications.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class User {
    public int id;
    public String title;
    public String name;
    public String street;
    public int plz;
    public String city;
    public String land;
    public int abteilung;

    public String toString() {
        return id + " - " + title + " - " + name + " - " + street + " - " + plz + " - " + city + " - " + land + " - " + abteilung;
    }

    public static ObservableList<User> loadFile() {
        ObservableList<User> result = FXCollections.observableArrayList();
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("users.csv"));
            try {
                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    s = s.replace("\"", ""); // ersetze alle " in der Zeile
                    User u = new User();

                    String[] words = s.split(";");
                    u.id = Integer.parseInt(words[0]);
                    u.title = words[1];
                    u.name = words[2];
                    u.street = words[3];
                    u.plz = Integer.parseInt(words[4]);
                    u.city = words[5];
                    u.land = words[6];
                    u.abteilung = Integer.parseInt(words[7]);

                    result.add(u); // füge Artikel zur Liste hinzu
                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
        }

        return result;
    }
}
