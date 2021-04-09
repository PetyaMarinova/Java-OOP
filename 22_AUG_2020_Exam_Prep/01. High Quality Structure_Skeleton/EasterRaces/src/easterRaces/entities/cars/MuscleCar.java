package easterRaces.entities.cars;

import static easterRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class MuscleCar extends BaseCar{
    public static final double CUBIC_CENTIMETERS = 5000;
    public static final int MIN_HORSE_POWER = 400;
    public static final int MAX_HORSE_POWER = 600;


    public MuscleCar(String model, int horsePower) {
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
