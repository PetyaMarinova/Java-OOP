package p02VehicleExtensionLastTry;

public class Bus extends Vehicle {
    private static final boolean DEFAULT_IS_EMPTY = true;
    private boolean isEmpty;

    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
        setEmpty(DEFAULT_IS_EMPTY);
    }

    public void setEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    @Override
    public void setFuelConsumption(double fuelConsumption) {
        if (!this.isEmpty){
            super.setFuelConsumption(fuelConsumption + 1.4);
        }
    }

    @Override
    public String drive(double kilometers) {
        return "Bus "+ super.drive(kilometers);
    }
}
