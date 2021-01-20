package applications.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Priority {
    public int id;
    public String name;

    public String toString() {
        return id + " - " + name;
    }

    public void delete() {
        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM priorities WHERE priority_id = " + id);
        } catch (SQLException throwables) {
        }
    }

    public void update() {
        try {
            Connection connection = AccessDb.getConnection();

            PreparedStatement statement = null;
            statement = connection.prepareStatement("UPDATE priorities SET name = ? WHERE priority_id = ?");
            statement.setString(1, name);
            statement.setInt(2, id);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static  ObservableList<Priority> loadlist() {
        ObservableList<Priority> list = FXCollections.observableArrayList();

        try{
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM priorities");

            while (result.next()) {
                Priority p = new Priority();
                p.id = result.getInt("priority_id");
                p.name = result.getString("name");

                list.add(p);
            }
        } catch (SQLException throwables){

        }

        return list;
    }

    public static ObservableList<Priority> loadFile(String filename) {
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
