package easterRaces.core.interfaces;

import easterRaces.entities.cars.Car;
import easterRaces.entities.cars.MuscleCar;
import easterRaces.entities.cars.SportsCar;
import easterRaces.entities.drivers.Driver;
import easterRaces.entities.drivers.DriverImpl;
import easterRaces.entities.racers.Race;
import easterRaces.entities.racers.RaceImpl;
import easterRaces.repositories.interfaces.CarRepository;
import easterRaces.repositories.interfaces.RaceRepository;
import easterRaces.repositories.interfaces.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static easterRaces.common.ExceptionMessages.*;
import static easterRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private Repository<Car> motorcycleRepository;
    private Repository<Race> raceRepository;
    private Repository<Driver> riderRepository;

    public ControllerImpl(Repository<Driver> riderRepository, Repository<Car> motorcycleRepository, Repository<Race> raceRepository) {
        this.riderRepository = riderRepository;
        this.motorcycleRepository = motorcycleRepository;
        this.raceRepository = raceRepository;

    }

    @Override
    public String createDriver(String driver) {
        Driver searchDriverInRepo = this.riderRepository.getByName(driver);
        if (searchDriverInRepo != null){
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS,driver));
        }
        Driver driverCreated = new DriverImpl(driver);
        this.riderRepository.add(driverCreated);
        return String.format(DRIVER_CREATED,driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        Car searchCar = this.motorcycleRepository.getByName(model);
        if (searchCar != null){
            throw new IllegalArgumentException(String.format(CAR_EXISTS,model));
        }
        Car car = null;
        if (type.equals("Muscle")){
            car = new MuscleCar(model,horsePower);
        } else if (type.equals("Sports")){
            car = new SportsCar(model, horsePower);
        }
        this.motorcycleRepository.add(car);
        assert car != null;
        return String.format(CAR_CREATED,car.getClass().getSimpleName(),model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driverWeSearch = this.riderRepository.getByName(driverName);
        if (driverWeSearch == null){
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND,driverName));
        }
        Car carWeSearch = this.motorcycleRepository.getByName(carModel);
        if (carWeSearch == null){
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND,carModel));
        }
        this.riderRepository.getByName(driverName).addCar(carWeSearch);
        return String.format(CAR_ADDED,driverName,carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race raceWeSearch = this.raceRepository.getByName(raceName);
        if (raceWeSearch == null){
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND,raceName));
        }
        Driver driverWeSearch = this.riderRepository.getByName(driverName);
        if (driverWeSearch == null){
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND,driverName));
        }
        raceWeSearch.addDriver(driverWeSearch);
        return String.format(DRIVER_ADDED,driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race raceWeSearch = this.raceRepository.getByName(raceName);
        if (raceWeSearch == null){
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND,raceName));
        }
        int laps = raceWeSearch.getLaps();
        List<Driver> winners = raceWeSearch.getDrivers().stream().sorted((d1, d2) -> {
            return Double.compare(d2.getCar().calculateRacePoints(laps), d1.getCar().calculateRacePoints(laps));
        }).limit(3).collect(Collectors.toList());
        if (winners.size() < 3){
            throw new IllegalArgumentException(String.format(RACE_INVALID,raceName,3));
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(DRIVER_FIRST_POSITION,winners.get(0).getName(),raceName))
                .append(System.lineSeparator())
                .append(String.format(DRIVER_SECOND_POSITION,winners.get(1).getName(),raceName))
                .append(System.lineSeparator())
                .append(String.format(DRIVER_THIRD_POSITION,winners.get(2).getName(),raceName))
                .append(System.lineSeparator());

        this.raceRepository.remove(raceWeSearch);

        return sb.toString().trim();
    }

    @Override
    public String createRace(String name, int laps) {
        Race raceWeSearch = this.raceRepository.getByName(name);
        if (raceWeSearch != null){
            throw new IllegalArgumentException(String.format(RACE_EXISTS,name));
        }
        Race race = new RaceImpl(name, laps);
        this.raceRepository.add(race);
        return String.format(RACE_CREATED,name);
    }
}
