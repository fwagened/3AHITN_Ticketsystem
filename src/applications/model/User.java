package applications.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class User {
    public int id;
    public String title;
    public String name;
    public String street;
    public int plz;
    public String city;
    public String land;
    public Department abteilung;

    public String toString() {
        return id + " - " + title + " - " + name + " - " + street + " - " + plz + " - " + city + " - " + land + " - " + abteilung;
    }

    public User(int id, String title, String name, String street, int zip, String city, String land, int departmentId) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.street = street;
        this.plz = zip;
        this.city = city;
        this.land = land;

        this.abteilung = Department.getById(departmentId);
    }

    public void delete() {
        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM users WHERE user_id = " + id);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        try {
            Connection connection = AccessDb.getConnection();

            PreparedStatement statement = null;
            statement = connection.prepareStatement("UPDATE users SET name = ?, title = ?, street = ?, zip = ?, city = ?, country = ?, department_id = ? WHERE user_id = ? ");
            statement.setString(1, name);
            statement.setString(2, title);
            statement.setString(3, street);
            statement.setInt(4, plz);
            statement.setString(5, city);
            statement.setString(6, land);
            statement.setInt(7, abteilung.departmentId);

            statement.setInt(8, id);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static  ObservableList<User> loadlist() {
        ObservableList<User> list = FXCollections.observableArrayList();

        try{
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users");

            while (result.next()) {
                User u = new User(result.getInt("user_id"),
                        result.getString("name"),
                        result.getString("title"),
                        result.getString("street"),
                        result.getInt("zip"),
                        result.getString("city"),
                        result.getString("country"),
                        result.getInt("department_id"));

                list.add(u);
            }
        } catch (SQLException throwables){

        }

        return list;
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
                    String[] words = s.split(";");

                    User u = new User(Integer.parseInt(words[0]), words[1], words[2], words[3], Integer.parseInt(words[4]), words[5], words[6], Integer.parseInt(words[7]));

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
