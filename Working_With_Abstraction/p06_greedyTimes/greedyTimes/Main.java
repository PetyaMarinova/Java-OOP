
package workingWithAbstraction.greedyTimes;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long bagCapacity = Long.parseLong(scanner.nextLine());
        String[] input = scanner.nextLine().split("\\s+");

        Bag bag = new Bag(bagCapacity);

        for (int i = 0; i < input.length; i += 2) {
            String nameItem = input[i];
            long amount = Long.parseLong(input[i + 1]);

            if (nameItem.length() == 3) {
                //add cash
                bag.addCash(nameItem, amount);
            } else if (nameItem.toLowerCase().endsWith("gem")) {
                //add gem
                bag.addGem(nameItem, amount);
            } else if (nameItem.toLowerCase().equals("gold")) {
                //add gold
                bag.addGold(amount);
            }
        }
        bag.printResult();
    }
}