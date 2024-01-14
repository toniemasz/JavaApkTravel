package pl.java.project;

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
    double kilometres;
    String fromPlace;
    String toPlace;
    String duration;
    public Travel(String title, double kilometres, String fromPlace, String toPlace, String duration) {
        this.title = title;
        this.kilometres = kilometres;
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public double getKilometres() {
        return kilometres;
    }

    public void setKilometres(double kilometres) {
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String fuelCost(double fuelConsumption, double fuelCost){
        double totalFuelCost = fuelConsumption * fuelCost;
        totalFuelCost = roundToTwoDecimalPlaces(totalFuelCost);
        return String.valueOf(totalFuelCost);
    }

    private double roundToTwoDecimalPlaces(double value) {
        // Mnożymy przez 100, zaokrąglamy, a potem dzielimy przez 100
        return Math.round(value * 100.0) / 100.0;
    }

    public double fuelConsumption(double fuelConsumptionPer100km){
        double totalFuelConsumption = (kilometres)/100 * fuelConsumptionPer100km;
        totalFuelConsumption = roundToTwoDecimalPlaces(totalFuelConsumption);
        return totalFuelConsumption;
    }
}
