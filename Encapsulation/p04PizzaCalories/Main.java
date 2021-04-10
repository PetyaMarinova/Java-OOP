package p04PizzaCalories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputPizza = scanner.nextLine().split("\\s+");
        String[] inputDough = scanner.nextLine().split("\\s+");

        String pizzaName = inputPizza[1];
        int numberOfToppings = Integer.parseInt(inputPizza[2]);

        Pizza pizza;
        try {
            pizza = new Pizza(pizzaName, numberOfToppings);

            String flourType = inputDough[1];
            String bakingTechnique = inputDough[2];
            double weight = Double.parseDouble(inputDough[3]);
            Dough dough = new Dough(flourType, bakingTechnique, weight);
            pizza.setDough(dough);

            String inputTopping = scanner.nextLine();
            while (!inputTopping.equals("END")) {
                String[] inputArray = inputTopping.split("\\s+");
                String toppingType = inputArray[1];
                double weightTopping = Double.parseDouble(inputArray[2]);

                Topping topping = new Topping(toppingType, weightTopping);
                pizza.addTopping(topping);

                inputTopping = scanner.nextLine();
            }
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
            return;
        }
        System.out.println(String.format("%s - %.2f", pizza.getName(), pizza.getOverallCalories()));
    }
}
