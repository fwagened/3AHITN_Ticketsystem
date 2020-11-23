package applications.model;

public class Status {
    public int id;
    public String status;

    @Override
    public String toString() {
        return id + " - " + status;
    }
}
