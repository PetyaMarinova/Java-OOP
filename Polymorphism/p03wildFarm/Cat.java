package wildFarm;

import java.text.DecimalFormat;

public class Cat extends Felime {
    private String breed;

    public Cat(String animalType, String animalName, double animalWeight, String livingRegion, String breed) {
        super(animalType, animalName, animalWeight, livingRegion);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Meowwww");
    }

//    Cat[Gray, Persian, 1.1, Home, 4]
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return String.format("%s[%s, %s, %s, %s, %d]",this.getClass().getSimpleName(),
                this.getAnimalName(),this.getBreed(),df.format(this.getAnimalWeight()), this.getLivingRegion(), this.getFoodEaten());
    }
}
