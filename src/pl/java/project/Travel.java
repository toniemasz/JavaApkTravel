package pl.java.project;

public class Travel {
    int kilometres;
    String fromPlace;

    String toPlace;
    String date;
    public Travel(int kilometres, String fromPlace, String toPlace, String date) {
        this.kilometres = kilometres;
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.date = date;
    }

    public int getKilometres() {
        return kilometres;
    }

    public void setKilometres(int kilometres) {
        this.kilometres = kilometres;
    }

    public String getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    public String getToPlace() {
        return toPlace;
    }

    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
