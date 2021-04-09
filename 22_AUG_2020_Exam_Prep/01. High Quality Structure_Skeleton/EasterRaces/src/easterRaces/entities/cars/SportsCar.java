package easterRaces.entities.cars;

import static easterRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class SportsCar extends BaseCar {
    public static final double CUBIC_CENTIMETERS = 3000;
    public static final int MIN_HORSE_POWER = 250;
    public static final int MAX_HORSE_POWER = 450;

    public SportsCar(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETERS);
    }

    @Override
    public void setHorsePower(int horsePower) {
        if (horsePower < MIN_HORSE_POWER || horsePower > MAX_HORSE_POWER ){
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER,horsePower));
        }
        super.setHorsePower(horsePower);
    }
}
