package aquarium;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AquariumTests {
    private Aquarium aquarium;
    private Fish fish1;
    private Fish fish2;

    @Before
    public void setUp(){
        this.aquarium = new Aquarium("Water", 15);
        this.fish1 = new Fish("Red");
        this.fish2 = new Fish("Blue");
    }

    @Test (expected = NullPointerException.class)
    public void testSetNullName(){
        Aquarium nullName = new Aquarium(null, 20);
    }

    @Test (expected = NullPointerException.class)
    public void testEmptyName(){
        Aquarium nullName = new Aquarium("    ", 20);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCapacityBelowZero(){
        Aquarium nullName = new Aquarium("Green", -2);
    }

    @Test
    public void testRemoveFishCorrectly(){
        this.aquarium.add(fish1);
        this.aquarium.add(fish2);
        this.aquarium.remove("Red");
        assertEquals(1, this.aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNullFishWillFail(){
        this.aquarium.add(fish1);
        this.aquarium.add(fish2);
        this.aquarium.remove("NonExist");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSellNonExistingFishWillFail(){
        this.aquarium.add(fish1);
        this.aquarium.add(fish2);
        this.aquarium.sellFish("NonExisting");
    }

    @Test
    public void testSellFishCorrectly(){
        this.aquarium.add(fish1);
        this.aquarium.add(fish2);
        Fish soldFish = this.aquarium.sellFish("Red");
        assertEquals(fish1, soldFish);
        assertFalse(fish1.isAvailable());
        assertEquals(2,this.aquarium.getCount());
    }

    @Test
    public void testReport() {
        this.aquarium.add(fish1);
        this.aquarium.add(fish2);
        assertEquals(2, this.aquarium.getCount());

        assertEquals("Fish available at Water: Red, Blue",
                this.aquarium.report());
    }

    @Test
    public void testRemoveCorrectly(){
        this.aquarium.add(fish1);
        this.aquarium.add(fish2);
        this.aquarium.remove("Red");
        assertEquals(1,this.aquarium.getCount());
    }

    @Test
    public void testSellFishMethod(){
        this.aquarium.add(fish1);
        this.aquarium.add(fish2);
        this.aquarium.sellFish("Red");
        assertFalse(fish1.isAvailable());
    }

    @Test
    public void testGetCapacity(){
        assertEquals(15, this.aquarium.getCapacity());
    }

    @Test
    public void testGetName(){
        assertEquals("Water", this.aquarium.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfAquariumIsFull(){
        Aquarium fakeAquarium = new Aquarium("One", 1);
        fakeAquarium.add(fish1);
        fakeAquarium.add(fish2);
    }

}

