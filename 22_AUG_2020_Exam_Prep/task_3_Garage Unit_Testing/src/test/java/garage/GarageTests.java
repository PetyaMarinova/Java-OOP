package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GarageTests {
    private Garage garage;
    private Car car1;
    private Car car2;

    @Before
    public void setUp(){
        this.garage = new Garage();
        this.car1 = new Car("Opel", 50, 250.00);
        this.car2 = new Car("Zastava", 30, 50.00);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetCarsUnmodifiable(){
        this.garage.getCars().remove(new Car("Ford", 80, 500.00));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetCarsUnmodifiableList(){
        this.garage.getCars().add(new Car("Ford", 80, 500.00));
    }

    @Test
    public void testGetCount(){
        this.garage.addCar(car1);
        this.garage.addCar(car2);
        assertEquals(2,this.garage.getCount());
    }

    @Test
    public void testGetCountNull(){
        assertEquals(0, this.garage.getCount());
    }

    @Test
    public void testFindAllCarsWithMaxSpeed(){
        this.garage.addCar(car1);
        this.garage.addCar(car2);
        List<Car> actualMaxCars = this.garage.findAllCarsWithMaxSpeedAbove(35);
        assertNotNull(this.garage.findAllCarsWithMaxSpeedAbove(35));
        assertEquals(1,actualMaxCars.size());
        assertEquals(car1,this.garage.findAllCarsWithMaxSpeedAbove(35).get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullCarWillFail(){
        this.garage.addCar(car1);
    }

    @Test
    public void testAddCarCorrectly(){
        this.garage.addCar(car1);
        this.garage.addCar(car2);
        assertNotNull(this.garage.getCars());
        assertEquals(2,this.garage.getCount());
        assertEquals(car1,this.garage.getCars().get(0));
    }

    @Test
    public void testNoneMostExpensiveCarWhenNoCarsInGarage(){
        assertNull(this.garage.getTheMostExpensiveCar());
    }

    @Test
    public void testNoCarsWithMaxSpeedIfNoCarsInGarage(){
        assertEquals(0,this.garage.findAllCarsWithMaxSpeedAbove(20).size());
    }

    @Test
    public void testCarsWithMaxSpeed(){
        this.garage.addCar(car1);
        this.garage.addCar(car2);
        assertNotNull(this.garage.findAllCarsWithMaxSpeedAbove(35));
        assertEquals(1,this.garage.findAllCarsWithMaxSpeedAbove(35).size());
        assertEquals(car1,this.garage.findAllCarsWithMaxSpeedAbove(35).get(0));
    }

    @Test
    public void testGetTheMostExpensiveCarCorrectly(){
        this.garage.addCar(car1);
        this.garage.addCar(car2);
        Car expectedCar = car1;
        assertEquals(expectedCar, this.garage.getTheMostExpensiveCar());
    }

    @Test
    public void testFindAllCarsByBrand(){
        this.garage.addCar(car1);
        this.garage.addCar(car2);
        assertNotNull(this.garage.findAllCarsByBrand("Opel"));
        assertEquals(1,this.garage.findAllCarsByBrand("Opel").size());
        assertEquals(car1,this.garage.findAllCarsByBrand("Opel").get(0));
    }

    @Test
    public void testFindNoneCarsIfGarageIsEmpty(){
        assertEquals(0,this.garage.findAllCarsByBrand("Opel").size());
    }
}