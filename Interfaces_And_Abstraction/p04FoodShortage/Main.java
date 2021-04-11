package p04FoodShortage;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       int n = Integer.parseInt(scanner.nextLine());

       Map<String, Buyer> allPeople = new LinkedHashMap<>();
        for (int i = 0; i < n ; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            if (tokens.length == 4){
                Citizen citizen = new Citizen(tokens[0],Integer.parseInt(tokens[1]),tokens[2],tokens[3]);
                allPeople.put(tokens[0], citizen);
            } else {
                Rebel rebel = new Rebel(tokens[0],Integer.parseInt(tokens[1]), tokens[2]);
                allPeople.put(tokens[0], rebel);
            }
        }

        String input = scanner.nextLine();
        while(!input.equals("End")){
            String name = input;
            if (allPeople.containsKey(name)) {
                allPeople.get(name).buyFood();
            }
            input = scanner.nextLine();
        }
        int totalAmount = 0;
        for (Buyer person : allPeople.values()) {
            totalAmount = totalAmount + person.getFood();
        }
        System.out.println(totalAmount);
    }
}
