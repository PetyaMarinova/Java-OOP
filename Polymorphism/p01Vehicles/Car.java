package p01Vehicles;

import java.text.DecimalFormat;

public class Car implements Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;

    public Car(double fuelQuantity, double fuelConsumption) {
        this.setFuelQuantity(fuelQuantity);
        this.setFuelConsumption(fuelConsumption);
    }

    public double getFuelQuantity() {
        return this.fuelQuantity;
    }

    private void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public double getFuelConsumption() {
        return this.fuelConsumption;
    }

    private void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption + 0.9;
    }

    @Override
    public String driving(double distance) {
        String output = null;
        double fuelNeededForCurrentDistance = distance * getFuelConsumption();
        if (fuelNeededForCurrentDistance <= getFuelQuantity()){
            setFuelQuantity(getFuelQuantity() - fuelNeededForCurrentDistance);
            DecimalFormat decimalFormat = new DecimalFormat("###.##");
            output = String.format("Car travelled %s km",decimalFormat.format(distance));
        } else {
            output = "Car needs refueling";
        }
        return output;
    }

    @Override
    public void refueling(double liters) {
       setFuelQuantity(getFuelQuantity() + liters);
    }
}
