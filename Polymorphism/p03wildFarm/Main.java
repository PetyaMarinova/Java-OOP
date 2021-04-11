package wildFarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        List<Animal> animals = new ArrayList<>();
        while(!line.equals("End")){
        String[] animalInfo = line.split("\\s+");
        String type = animalInfo[0];
        String name = animalInfo[1];
        double weight = Double.parseDouble(animalInfo[2]);
        String livingRegion = animalInfo[3];

        Animal animal = null;
        switch (type){
            case "Mouse":
                animal = new Mouse(type, name, weight, livingRegion);
                break;
            case "Tiger":
                animal = new Tiger(type, name, weight, livingRegion);
                break;
            case "Cat":
                String breed = animalInfo[4];
                animal= new Cat(type, name, weight, livingRegion, breed);
                break;
            case "Zebra":
                animal = new Zebra(type, name, weight, livingRegion);
                break;
        }
        animal.makeSound();
        String[] foodTokens = scanner.nextLine().split("\\s+");
        String typeFood = foodTokens[0];
        int foodQuantity = Integer.parseInt(foodTokens[1]);

        Food food = null;
        if (typeFood.equals("Vegetable")){
            food = new Vegetable(foodQuantity);
        } else {
            food = new Meat(foodQuantity);
        }
        try {
            animal.eat(food);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        
        animals.add(animal);

        line = scanner.nextLine();
        }
        for (Animal animal : animals) {
            System.out.println(animal.toString());
        }
    }
}
