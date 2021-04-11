package p01Vehicles;

import java.text.DecimalFormat;

public class Truck implements Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;

    public Truck(double fuelQuantity, double fuelConsumption) {
        this.setFuelQuantity(fuelQuantity);
        this.setFuelConsumption(fuelConsumption);
    }

    private void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    private void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption + 1.6;
    }

    public double getFuelQuantity() {
        return this.fuelQuantity;
    }

    public double getFuelConsumption() {
        return this.fuelConsumption;
    }

    @Override
    public String driving(double distance) {
        String output = null;
        double fuelNeededForCurrentDistance = distance * getFuelConsumption();
        if (fuelNeededForCurrentDistance <= getFuelQuantity()){
            setFuelQuantity(getFuelQuantity() - fuelNeededForCurrentDistance);
            DecimalFormat decimalFormat = new DecimalFormat("###.##");
            output = String.format("Truck travelled %s km",decimalFormat.format(distance));
        } else {
            output = "Truck needs refueling";
        }
        return output;
    }

    @Override
    public void refueling(double liters) {
        setFuelQuantity(getFuelQuantity() + liters * 0.95);
    }
}
