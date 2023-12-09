package pl.java.project;

import java.io.Serializable;

public class Travel implements Serializable {
    @Override
    public String toString() {
        return "Travel{" +
                "title='" + title + '\'' +
                ", kilometres=" + kilometres +
                ", fromPlace='" + fromPlace + '\'' +
                ", toPlace='" + toPlace + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    String title;
    int kilometres;
    String fromPlace;
    String toPlace;
    String date;
    public Travel(String title,int kilometres, String fromPlace, String toPlace, String date) {
        this.title = title;
        this.kilometres = kilometres;
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.date = date;
    }

    public String getTitle() {
        return title;
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
