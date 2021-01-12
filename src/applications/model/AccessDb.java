package applications.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccessDb {

    static {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection connection = null;
    //Statische Variablen sind in jeder Instanz gleich
    //Est steht überall dasselbe drinnen

    /**
     * Vereinfachtes Singleton - Pattern
     */

    public static Connection getConnection(){
        //Wenn connection noch nicht befüllt wurde (connection == null)
        if (connection == null){
            try{
               connection = DriverManager.getConnection("jdbc:ucanaccess://D:/Schule/HTL-Steyr/HTL-Steyr 3.Klasse/ITP2/Ticketsystem/db/Ticketsystem Felix Wageneder.accdb");

            } catch (SQLException throwables){

            }
        }
        return connection;

    }

}
