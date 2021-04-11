package p03BirthdayCelebrations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        List<Birthable> petsAndCitizens = new ArrayList<>();

        while (!input.equals("End")) {
            String[] tokens = input.split("\\s+");
            if (tokens.length == 5) {
                Citizen citizen = new Citizen(tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4]);
                petsAndCitizens.add(citizen);
            } else {
                if (tokens[0].equals("Pet")) {
                    Pet pet = new Pet(tokens[1], tokens[2]);
                    petsAndCitizens.add(pet);
                }
            }
            input = scanner.nextLine();
        }
        String yearToSearch = scanner.nextLine();
        for (Birthable petsOrCitizen : petsAndCitizens) {
            if (petsOrCitizen.getBirthDate().endsWith(yearToSearch)) {
                System.out.println(petsOrCitizen.getBirthDate());
            }
        }

    }
}
