package applications.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Status {
    public int id;
    public String status;

    @Override
    public String toString() {
        return id + " - " + status;
    }

    public static ObservableList<Status> loadFile() {
        ObservableList<Status> result = FXCollections.observableArrayList();
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("stati.csv"));
            try {
                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    s = s.replace("\"", ""); // ersetze alle " in der Zeile
                    Status status = new Status();

                    String[] words = s.split(";");
                    status.id = Integer.parseInt(words[0]);
                    status.status = words[1];

                    result.add(status);
                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
            io.printStackTrace();
        }

        return result;
    }


}

