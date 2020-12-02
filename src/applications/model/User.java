package applications.model;

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
}
