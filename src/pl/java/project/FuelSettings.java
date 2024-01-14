package pl.java.project;

public class FuelSettings {
    double fuelConsumption;
    double fuelPrice;

    public FuelSettings(double fuelConsumption, double fuelPrice) {
        this.fuelConsumption = fuelConsumption;
        this.fuelPrice = fuelPrice;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getFuelPrice() {
        return fuelPrice;
    }

    public void setFuelPrice(double fuelPrice) {
        this.fuelPrice = fuelPrice;
    }
}
