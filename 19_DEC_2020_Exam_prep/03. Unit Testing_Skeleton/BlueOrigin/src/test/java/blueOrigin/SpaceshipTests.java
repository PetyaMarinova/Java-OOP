package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpaceshipTests {
    private Spaceship spaceship;
    private Astronaut astronaut1;
    private Astronaut astronaut2;

    @Before
    public void setUp(){
        spaceship = new Spaceship("Armagedon", 15);
        astronaut1 = new Astronaut("Petya", 77.77);
        astronaut2 = new Astronaut("Kiril", 42.42);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullName(){
        Spaceship nullNameSpaceship = new Spaceship(null, 12);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetZeroCapacity(){
        Spaceship zeroCapacitySpaceship = new Spaceship("Poseidon", -22);
    }

    @Test
    public void testGetCountZeroAstronauts(){
        assertEquals(0,this.spaceship.getCount());
    }

    @Test
    public void testGetCountManyAstronauts(){
        this.spaceship.add(astronaut1);
        this.spaceship.add(astronaut2);
        assertEquals(2,this.spaceship.getCount());
    }

    @Test
    public void testGetName(){
        String name = "Armagedon";
        assertEquals(name,this.spaceship.getName());
    }

    @Test
    public void testGetCapacity(){
        assertEquals(15, this.spaceship.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAstronautsButSpaceshipIsFull(){
        Spaceship newSpaceship = new Spaceship("Full", 2);
        newSpaceship.add(astronaut1);
        newSpaceship.add(astronaut2);
        newSpaceship.add(new Astronaut("Mitko", 99.00));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddButAstronautExist(){
        this.spaceship.add(astronaut1);
        this.spaceship.add(astronaut2);
        this.spaceship.add(astronaut1);
    }

    @Test
    public void testAddAstronautCorrectly(){
        this.spaceship.add(astronaut1);
        this.spaceship.add(astronaut2);
        assertEquals(2, this.spaceship.getCount());
    }

    @Test
    public void testRemoveNotExistingAstronaut(){
        this.spaceship.add(astronaut1);
        this.spaceship.add(astronaut2);
        assertFalse(this.spaceship.remove("Gosho"));
        assertEquals(2,this.spaceship.getCount());
    }

    @Test
    public void testRemoveExistingAstronaut(){
        this.spaceship.add(astronaut1);
        this.spaceship.add(astronaut2);
        assertTrue(this.spaceship.remove("Petya"));
        assertEquals(1,this.spaceship.getCount());
    }
}
