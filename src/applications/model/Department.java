package applications.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Department {
    public int departmentId = 0;
    public String departmentName = "";



    public String toString() {
        return departmentId + " - " + departmentName;
    }

    public void update() {
        try {
            Connection connection = AccessDb.getConnection();

            PreparedStatement statement = null;
            statement = connection.prepareStatement("UPDATE departments SET name = ? WHERE department_id = ?");
            statement.setString(1, departmentName);
            statement.setInt(2, departmentId);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static  ObservableList<Department> loadlist() {
        ObservableList<Department> list = FXCollections.observableArrayList();

        try{
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM departments");

            while (result.next()) {
                Department d = new Department();
                d.departmentId = result.getInt("department_id");
                d.departmentName = result.getString("Name");

                list.add(d);
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }

        return list;
    }

    public static ObservableList<Department> loadFile() {
        ObservableList<Department> result = FXCollections.observableArrayList();
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("departments.csv"));
            try {
                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    s = s.replace("\"", ""); // ersetze alle " in der Zeile
                    Department department = new Department();

                    String[] words = s.split(";");
                    department.departmentId = Integer.parseInt(words[0]);
                    department.departmentName = words[1];

                    result.add(department);
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
