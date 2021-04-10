package animals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        List<Animal> animals = new ArrayList<>();

        while(!input.equals("Beast!")){
            String typeOfAnimal = input;
            String[] tokens = scanner.nextLine().split("\\s+");
            try {switch (typeOfAnimal){
                case "Cat":
                    Cat cat = new Cat(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
                    animals.add(cat);
                    break;
                case "Dog":
                    Dog dog = new Dog(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
                    animals.add(dog);
                    break;
                case "Frog":
                    Frog frog = new Frog(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
                    animals.add(frog);
                    break;
                case "Kitten":
                    Kitten kitten = new Kitten(tokens[0], Integer.parseInt(tokens[1]));
                    animals.add(kitten);
                    break;
                case "Tomcat":
                    Tomcat tomcat = new Tomcat(tokens[0], Integer.parseInt(tokens[1]));
                    animals.add(tomcat);
                    break;
            }
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
            input = scanner.nextLine();
        }
        animals.forEach(System.out::println);
    }
}
