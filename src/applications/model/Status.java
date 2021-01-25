package applications.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Status {
    public int id;
    public String status;

    @Override
    public String toString() {
        return id + " - " + status;
    }

    public void delete() {
        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM stati WHERE status_id = " + id);
        } catch (SQLException throwables) {
        }
    }

    public void update() {
        try {
            Connection connection = AccessDb.getConnection();

            PreparedStatement statement = null;
            statement = connection.prepareStatement("UPDATE stati SET name = ? WHERE status_id = ?");
            statement.setString(1, status);
            statement.setInt(2, id);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static  ObservableList<Status> loadlist() {
        ObservableList<Status> list = FXCollections.observableArrayList();

        try{
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM stati");

            while (result.next()) {
                Status s = new Status();
                s.id = result.getInt("status_id");
                s.status = result.getString("name");

                list.add(s);
            }
        } catch (SQLException throwables){

        }

        return list;
    }

    public static ObservableList<Status> loadFile(String filename) {
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

