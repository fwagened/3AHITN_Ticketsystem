package applications.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Department {
    public int departmentId = 0;
    public String departmentName = "";



    public String toString() {
        return departmentId + " - " + departmentName;
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
