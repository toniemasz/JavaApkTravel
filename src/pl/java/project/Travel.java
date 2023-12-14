package pl.java.project;

import java.io.Serializable;

public class Travel {
    @Override
    public String toString() {
        return "Travel{" +
                "title='" + title + '\'' +
                ", kilometres=" + kilometres +
                ", fromPlace='" + fromPlace + '\'' +
                ", toPlace='" + toPlace + '\'' +
                ", date='" + duration + '\'' +
                '}';
    }

    String title;
    String kilometres;
    String fromPlace;
    String toPlace;
    String duration;
    public Travel(String title, String kilometres, String fromPlace, String toPlace, String duration) {
        this.title = title;
        this.kilometres = kilometres;
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public String getKilometres() {
        return kilometres;
    }

    public void setKilometres(String kilometres) {
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
