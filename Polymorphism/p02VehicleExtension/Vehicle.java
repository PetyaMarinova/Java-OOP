package p02VehicleExtensionLastTry;

import java.text.DecimalFormat;

public class Vehicle implements IVehicle {
    private double fuelQuantity;
    private double fuelConsumption;
    private double tankCapacity;

    public Vehicle(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        this.fuelQuantity = fuelQuantity;
        this.setFuelConsumption(fuelConsumption);
        this.tankCapacity = tankCapacity;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public String drive(double kilometers) {
        double fuelNeeded = this.fuelConsumption * kilometers;
        String result = "needs refueling";
        if (this.fuelQuantity >= fuelNeeded){
            DecimalFormat df = new DecimalFormat("#.##");
            result = String.format("travelled %s km",df.format(kilometers));
            this.fuelQuantity -= fuelNeeded;
        }
        return result;
    }

    @Override
    public void refuel(double liters) {
        if (liters <= 0){
            throw new IllegalArgumentException("Fuel must be a positive number");
        }
        if (this.fuelQuantity + liters > this.tankCapacity){
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }
        this.fuelQuantity += liters;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f",this.getClass().getSimpleName(), this.fuelQuantity);
    }
}
